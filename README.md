# 📋 TodoList Java + Spring Boot

Seja bem-vindo à **TodoList API**, uma aplicação simples criada com **Java + Spring Boot**, com o objetivo de gerenciar tarefas com autenticação básica.

🔗 **Link da API:** [https://todolist-java-x8dy.onrender.com](https://todolist-java-x8dy.onrender.com)

> ⚠️ **Importante:** a API está hospedada no plano gratuito da [Render](https://render.com), portanto, pode entrar em modo de hibernação. Se isso acontecer, a primeira requisição pode levar até **50 segundos** para "acordar" o servidor.

---

## 🧑‍💻 Autenticação

A autenticação é feita via **Basic Auth** com as credenciais de usuário para as endpoints de criar e atualizar tarefa(task).

Exemplo: 

Username: teste

Password: 123456

---

## 🛠 Endpoints Disponíveis

### ✅ Criar Usuário

**POST** `/users/create`  
Cria um novo usuário.

**Body (JSON):**

```json
{
  "name": "teste",
  "username": "teste",
  "password": "123456"
}
```

### ✅ Criar Tarefa

**POST** `/tasks/create`  
Cria uma nova tarefa.

**Body (JSON):**

```json
{
  "description": "Irei chamar o codebydias para uma conversa",
  "title": "Adicionar nova amizade",
  "priority": "EXTREAMING",
  "startAt": "2025-07-02T12:00:00",
  "endAt": "2025-07-04T12:00:00"
}
```

📌 Regras:

startAt: deve ser maior que a data/hora atual

endAt: não pode ser anterior à data atual

### ✅ Listar Tarefas

**GET** `/tasks/listing`  
Lista todas as tarefas do usuário autenticado.

Auth: Basic Auth

### ✅ Atualizar Tarefas

**PUT** `/tasks/update/{id}`  
Atualiza uma tarefa existente pelo Id da task(tarefa).

Auth: Basic Auth

**Body (JSON):**

```json
{
  "priority": "EXTREMELY HIGH",
  "title": "Não esquecer o CAFÉ"
}
```
