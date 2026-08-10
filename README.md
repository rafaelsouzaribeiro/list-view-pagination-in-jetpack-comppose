# 📱 Lista com Paginação - KMP Jetpack Compose (iOS e Android)

Um projeto multiplataforma desenvolvido com **Kotlin Multiplatform (KMP)** e **Jetpack Compose** que demonstra implementação de lista com paginação usando **Paging 3**, integrando dados da **TMDB API**.

## ✨ Características

- 📋 Lista de filmes com paginação automática
- 🔄 Suporte multiplataforma (iOS e Android)
- 🎨 Interface com Jetpack Compose
- 🏗️ Arquitetura MVVM com Koin para injeção de dependência
- 🌐 Integração com TMDB API

---

## ⚙️ Configuração Inicial

### 1. Criar Conta no TMDB

1. Acesse https://www.themoviedb.org/signup
2. Preencha nome, e-mail e senha → clique em **Junte-se ao TMDB**
3. Confirme o e-mail recebido na sua caixa de entrada

### 2. Gerar o Access Token

1. Faça login em https://www.themoviedb.org/login
2. Clique no seu avatar (canto superior direito) → **Configurações**
3. No menu lateral esquerdo, clique em **API**
4. Na seção "Solicitar chave de API", clique em **click here**
5. Na página com a descrição "Is the intended use of our API for personal use?", clique em **This is for my own personal use only**
6. Uma popup será aberta, mantenha o checkbox marcado e clique em **Yes, this is for personal use**
7. Preencha o formulário, marque o checkbox de aceitar os termos de uso e clique em **Subscribe**
8. Após isso, você será redirecionado para "Subscription Details", clique em **Access your API key details here**
9. Na seção "Token de Leitura da API", copie o token completo

> ⚠️ **Importante**: Use o **Token de Leitura da API**, e não a Chave da API.

### 3. Colar o Token no Projeto

Abra o arquivo:

- `shared/src/commonMain/kotlin/com/example/listview/network/Secrets.kt`

Substitua `"YOUR_TMDB_ACCESS_TOKEN"` pelo seu token copiado:

```kotlin
package com.example.listview.network

object Secrets {
    const val TMDB_ACCESS_TOKEN = "YOUR_TMDB_ACCESS_TOKEN"
}


