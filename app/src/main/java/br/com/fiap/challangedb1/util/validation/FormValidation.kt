package br.com.fiap.challangedb1.util.validation

fun validacaoNomeEmail(texto: String): Boolean {
    var isValid = false

    if (texto.length < 71 && texto.length > 1 && texto.isNotEmpty()) {
        isValid = true
    }

    return isValid
}

fun validacaoSenha(texto: String): Boolean {
    var isValid = false

    if (texto.length < 16 && texto.length > 5 && texto.isNotEmpty()) {
        isValid = true
    }

    return isValid
}

fun validacaoGenero(texto: String): Boolean {
    var isValid = false

    if (texto.isNotEmpty()) {
        isValid = true
    }

    return isValid
}