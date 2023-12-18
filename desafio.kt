enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(var nome: String, var email: String, val id: Int){
    override fun equals(other: Any?) =
        other is Usuario && other.id == this.id

    override fun hashCode() = id
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel = Nivel.BASICO)

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }

    operator fun iterator(): Iterator<ConteudoEducacional> {
        return conteudos.iterator()
    }

}

fun main() {
    val user1 = Usuario("Alex", "alex@email.com", 1)
    val user2 = Usuario("Ana", "ana@email.com", 2)
    val user3 = Usuario("João", "joao@email.com", 3)

    val cursoKotlin = ConteudoEducacional("Curso de Kotlin", 480, Nivel.AVANCADO)
    val cursoAndroid = ConteudoEducacional("Curso de Android", 120, Nivel.INTERMEDIARIO)
    val cursoKotlinAndroid = ConteudoEducacional("Curso de Kotlin Android", 60, Nivel.BASICO)

    val formacaoAndroid = Formacao("Formação Android", listOf(cursoKotlin, cursoAndroid, cursoKotlinAndroid))
    val formacaoKotlin = Formacao("Formação Kotlin", listOf(cursoKotlin, cursoKotlinAndroid))

    formacaoAndroid.matricular(user1)
    formacaoAndroid.matricular(user2)
    formacaoKotlin.matricular(user1)
    formacaoKotlin.matricular(user3)

    println("Usuários matriculados na formação Android:")
    for (usuario in formacaoAndroid.inscritos) {
        println(usuario)
    }
    println()

    println("Usuários matriculados na formação Kotlin:")
    for (usuario in formacaoKotlin.inscritos) {
        println(usuario)
    }
    println()

    println("Conteúdos da formação Kotlin:")
    for (conteudo in formacaoKotlin) {
        println(conteudo)
    }
    println()

    println("Conteúdos da formação Android:")
    for (conteudo in formacaoAndroid) {
        println(conteudo)
    }

}