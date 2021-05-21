import pandas as pd
from bs4 import BeautifulSoup, NavigableString
from selenium import webdriver
import csv

# LANG = 'spa'
LANG = 'eng'

bookCounts = []
# bookCountsDCon.csv
with open('bookCounts.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    for row in csv_reader:
        bookCounts.append(row)
        line_count += 1

baseURL = 'https://www.churchofjesuschrist.org/study/scriptures/'
langEnding = '?lang=' + LANG

driver = webdriver.Chrome(executable_path='D:/AA_Files/Coding/chromedriver_win32/chromedriver.exe')

results = []
header = ["volume_id", "book_id", "chapter_id", "verse_id", "lang", "book_name", "chapter_number", "verse_number", "scripture_text"]
results.append(header)

lastVol = 'ot'
# lastVol = 'dc-testament'
volume_id = 1
book_id = 0
chapter_id = 0
verse_id = 0

partNum = 1
outputFileBaseName = 'scriptures-'
partBaseName = '-pt'
outputFileType = '.csv'

outputFileName = ''


def writeFile(filename, results):
    with open(filename, 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerows(results)


def section_verse_parser(verses, verse_id, results):
    for verse in verses[0].find_all('p'):
        verse_contents_len = len(verse.contents)

        verse_number = 0
        scripture_text = ""
        part_counter = 0
        if verse.has_attr('class') and verse['class'][0] != 'study-summary' and verse['class'][0] != 'closing' and verse['class'][0] != 'signature':
            verse_id += 1
            for part in verse.contents:
                numUpdated = False
                isStr, scripture_text = isString(part, scripture_text)
                if not isStr:
                    verse_number, numUpdated = verse_number_tag(part, verse_number)
                    scripture_text = line(part, scripture_text)  # , verse_contents_len, part_counter
                    scripture_text = study_note_ref_clarity_word_question_answer(part, scripture_text)
                    scripture_text = diety_name_small_caps_selah(part, scripture_text)
                    scripture_text = language(part, scripture_text)
                if verse.has_attr('class') and 'contains-line' in verse['class'] and part_counter < verse_contents_len - 1 and not numUpdated:  # partCount == len(tag.contents) - 1 and
                    scripture_text += ' '
                part_counter += 1

            verseinfo = [volume_id, book_id, chapter_id, verse_id, LANG, bookInfo[3], chapter, verse_number,
                         scripture_text]
            results.append(verseinfo)
    return verse_id, results

    # check if section if is for and recurse
    # else for p tags
    # Check for line seg
    # Get verse #


def verse_number_tag(tag, verse_number):
    if tag.has_attr('class') and tag['class'][0] == 'verse-number':
        return int(tag.string), True
    else:
        return verse_number, False

def isString(tag, scripture_text):
    if isinstance(tag, NavigableString):
        scripture_text += tag
        return True, scripture_text
    return False, scripture_text


def line(tag, scripture_text):  # , verse_contents_len, part_counter
    if tag.has_attr('class') and tag['class'][0] == 'line':
        for partCount in range(len(tag.contents)):
            part = tag.contents[partCount]
            isStr, scripture_text = isString(part, scripture_text)
            if not isStr:
                scripture_text = study_note_ref_clarity_word_question_answer(part, scripture_text)
                scripture_text = diety_name_small_caps_selah(part, scripture_text)
                scripture_text = language(part, scripture_text)
    return scripture_text

def language(tag, scripture_text):
    if tag.has_attr('class') and tag['class'][0] == 'language':
        for part in tag.contents:
            isStr, scripture_text = isString(part, scripture_text)
            if not isStr:
                if part.name == 'i':
                    scripture_text += part.contents[0]
                else:
                    scripture_text = study_note_ref_clarity_word_question_answer(part, scripture_text)
    return scripture_text

def study_note_ref_clarity_word_question_answer(tag, scripture_text): # and scripture_ref
    cval = None
    if tag.name != 'em':
        cval = tag['class'][0]
    if tag.name == 'em' or cval == 'study-note-ref' or cval == 'clarity-word' or cval == 'question' or cval == 'answer' or cval == 'scripture-ref':
        for part in tag.contents:
            isStr, scripture_text = isString(part, scripture_text)
            if not isStr:
                scripture_text = study_note_ref_clarity_word_question_answer(part, scripture_text)
                scripture_text = diety_name_small_caps_selah(part, scripture_text)
                scripture_text = language(part, scripture_text)
    return scripture_text


def diety_name_small_caps_selah(tag, scripture_text): # and language i
    if tag.has_attr('class') and (tag['class'][0] == 'diety-name' or tag['class'][0] == 'small-caps' or tag['class'][0] == 'selah'):
        for part in tag.contents:
            isStr, scripture_text = isString(part, scripture_text)
            if not isStr:
                scripture_text = diety_name_small_caps_selah(part, scripture_text)
                scripture_text = study_note_ref_clarity_word_question_answer(part, scripture_text)
                scripture_text = language(part, scripture_text)
    return scripture_text


for bookInfo in bookCounts:
    if lastVol != bookInfo[0] and bookInfo[1] != 'gen': # 'dc':
        volume_id += 1
        lastVol = bookInfo[0]
        outputFileName = outputFileBaseName + LANG + partBaseName + str(partNum) + outputFileType
        partNum += 1
        writeFile(outputFileName, results)
        results = []
    elif book_id == 20:
        outputFileName = outputFileBaseName + str(partNum) + outputFileType
        partNum += 1
        writeFile(outputFileName, results)
        results = []

    bookURL = baseURL + lastVol + "/" + bookInfo[1]
    book_id += 1
    for chapter in range(1, int(bookInfo[2]) + 1):
        chapterURL = bookURL + "/" + str(chapter) + langEnding
        chapter_id += 1
        driver.get(chapterURL)

        content = driver.page_source
        soup = BeautifulSoup(content)

        verses = soup.find_all(attrs='body-block')
        verse_id, results = section_verse_parser(verses, verse_id, results)

# Final book save
volume_id += 1
lastVol = bookInfo[0]
outputFileName = outputFileBaseName + LANG + partBaseName + str(partNum) + outputFileType
partNum += 1
writeFile(outputFileName, results)
results = []
driver.quit()
