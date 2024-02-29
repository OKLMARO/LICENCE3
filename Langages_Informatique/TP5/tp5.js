class Champignon {
    constructor(nom, poids, comestible){
        this.nom = nom
        this.poids = poids
        this.comestible = comestible
    }

    toString() {
        return `Nom : ${this.nom}, Poids : ${this.poids}g, Comestible : ${this.comestible}`
    }
}

class Morille extends Champignon {
    constructor(nom, poids){
        super(nom, poids, true)
    }
}

class Amanite extends Champignon {
    constructor(nom, poids, comestible, stries){
        super(nom, poids, comestible)
        this.stries = stries;
    }

    toString(){
        return super.toString() + `, Stries : ${this.stries}`
    }
}

class Golmotte extends Amanite {
    constructor(nom, poids){
        super(nom, poids, true, false)
    }
}

class Panthere extends Amanite {
    constructor(nom, poids){
        super(nom, poids, false, true)
    }
}

/* function selection(Champ) {
    let ChampCom = []
    
    for (let i = 0; i < Champ.length; i++) {
        if(Champ[i].comestible == true) {
            ChampCom.push(Champ[i])
        }
    }
    
    return ChampCom
} */

/* function selection(Champ){
    let ChampCom = []
    
    for (const iterator of Champ) {
        if(iterator.comestible == true){
            ChampCom.push(iterator)
        }
    }

    return ChampCom
} */

function selection(Champ){
    return Champ.filter(n => n.comestible == true)
}

let x = []

x.push(new Morille("Rudy", 500))
x.push(new Panthere("Alex", 600))
x.push(new Morille("Esteban", 500))

console.log(selection(x))