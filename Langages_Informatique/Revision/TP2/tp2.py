from bs4 import BeautifulSoup

kl = open("test.html", "r")
klsoup = BeautifulSoup(kl, "html.parser")

def titre(soup):
    return soup.find("title").text

print(titre(klsoup))