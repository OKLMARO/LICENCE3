sealed abstract class Expression {
    def evaluer():Int={
        this match
            case Entier(n) => n
            case Plus(e1, e2) => e1.evaluer()+e2.evaluer()
            case Min(e1, e2) => Math.min(e1.evaluer(),e2.evaluer())        
    }

    def simplifier() : Expression ={ // 4
    this match
        case Entier(n) => Entier(n)
        case Plus(e1,e2) => ((e1.evaluer(),e2.evaluer()) match
            case (n,(m)) =>{ if(n==0) then Entier(m)
                else if (m==0) then Entier(n) else  Plus(Entier(n),Entier(m))}
        )
        case Min(e1,e2) => ((e1.evaluer(),e2.evaluer()) match
            case ((n),(m)) =>{ if(n==0) then Entier(0)
                else if (m==0) then Entier(0) else  Min(Entier(n),Entier(m))}
        )
    }

    override def equals(x: Any): Boolean = {
        x match
            case x:Expression => x.evaluer()==this.evaluer()
            case _ => false
    }

    override def hashCode(): Int = {
        this.evaluer()*71
    }

    override def toString(): String = {
        this match
            case Entier(n) => ""+ n
            case Min(e1, e2) => "Min("+e1+","+e2+")"
            case Plus(e1, e2) => "Plus("+e1+"+"+e2+")"
        
    }
}
case class Entier(n:Int) extends  Expression{
    require(n>=0)
}
case class Plus(e1:Expression,e2:Expression) extends Expression{

}
case class Min(e1:Expression,e2:Expression) extends Expression{

}

def main(args : Array[String])={
    val e1 = Entier(3)
    val e2 = Entier(4)
    val e6 =Plus(Min(Entier(9),Entier(44)), Entier(0))
    val e3 = Plus(e1,e2)
    val e4 = Min(e1,e2)
    println(e3.evaluer())
    println(e4.evaluer())
    println(e6.simplifier())
    println(e4.simplifier())
    println(e3==e4)
    println(e3.hashCode())
    println(e4.hashCode())
    println(e3)
    println(e4)
}