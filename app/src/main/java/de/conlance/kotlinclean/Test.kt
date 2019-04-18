package de.conlance.kotlinclean

interface AttackType {
    fun getAttackType(): String
}

class Ranged : AttackType {
    override fun getAttackType(): String = "Ranged"
}

interface HeroType {
    fun getAttributeType(): String
}

class Strength : HeroType {
    override fun getAttributeType(): String = "Strength"
}

class Husker: AttackType by Ranged() {
    override fun getAttackType(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}