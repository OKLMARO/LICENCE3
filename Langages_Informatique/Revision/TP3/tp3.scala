sealed abstract class Formule{
    def substiuer(sub : Map[Variable, Formule]) : Formule = {
        this match {
            case Variable(nom) => if(sub.get(Variable(nom)) != None) then sub.get(Variable(nom)) else Variable(nom)
            case Non(form) => Non(form.substiuer(sub))
            case Et(formg, formd) => Et(formg.substiuer(sub), formd.substiuer(sub))
            case Ou(formg, formd) => Ou(formg.substiuer(sub), formd.substiuer(sub))
            case Vrai => Vrai
            case Faux => Faux
        }
    }

    def evaluer(sub : Map[Variable, Formule]) : Option[Boolean] = {
        this match {
            case Variable(nom) => 
            case
}

case class Variable(nom : String) extends Formule{
    override def toString = {
        nom
    }
}

case class Non(form : Formule) extends Formule{
    override def toString = {
        "non(" + form.toString + ")"
    }
}

case class Et(formg : Formule, formd : Formule) extends Formule{
    override def toString = {
        "(" + formg.toString + " et " + formd.toString + ")"
    }
}

case class Ou(formg : Formule, formd : Formule) extends Formule{
    override def toString = {
        "(" + formg.toString + " ou " + formd.toString + ")"
    }
}

case object Vrai extends Formule{
    override def toString = {
        "vrai"
    }
}

case object Faux extends Formule{
    override def toString = {
        "faux"
    }
}

