import requests
from bs4 import BeautifulSoup

kl = open("King's_Landing.html", "r")
klsoup = BeautifulSoup(kl, "html.parser")

def titre(soup):
    return soup.title.string

def afficher_h2(soup):
    for i in soup.find_all("h2"):
        print(i.string)

def nb_par_avec_lien(soup):
    compteur = 0
    for i in soup.find_all("p"):
        compteur += 1
    return compteur

def liens(adresse):
    page = requests.get(adresse)
    soup = BeautifulSoup(page, "html.parser")
    titre(soup)

print(liens("https://iceandfire.fandom.com/wiki/Petyr_Baelish"))
