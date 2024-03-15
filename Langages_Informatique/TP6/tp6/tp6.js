var imageElement = document.createElement('img');
imageElement.src = 'crepe.png';
imageElement.classList.add("c5")
document.body.append(imageElement);

var div = document.createElement('div');
div.id="stats";
div.classList.add("c3");

var div2 = document.createElement("div");
div2.textContent="Nombre de crepe :";
div2.classList.add("c1");

var div3 = document.createElement("div");
div3.textContent=0;
div3.id="compteur";
div3.classList.add("c1");

div.appendChild(div2);
div.appendChild(div3);


document.body.append(div);

imageElement.addEventListener('click', function(){
    div3.textContent++;
});

var div4 = document.createElement("div");
div4.textContent="Nombre de bigoudenes :";

var div5 = document.createElement("div");
div4.id="bigoudenes";
div4.textContent=1; 

var btn = document.createElement("input");
btn.type="button"

