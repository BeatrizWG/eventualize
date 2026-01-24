const API_URL = "http://localhost:8080/api/auth";

function limparErros(prefixo) {
    document
        .querySelectorAll(`[id^="${prefixo}"]`)
        .forEach(el => el.textContent = "");
}

function limparInputs(prefixo) {
    document
        .querySelectorAll(`[id^="${prefixo}"]`)
        .forEach(el => el.value = "");
}

function limparLogin() {
    limparInputs("login");
    limparErros("erro-login-");
    const msg = document.getElementById("msgLogin");
    msg.textContent = "";
    msg.classList.remove("erro", "sucesso");
}

function limparCadastro() {
    limparInputs("cadastro");
    limparErros("erro-");
    const msg = document.getElementById("msgCadastro");
    msg.textContent = "";
    msg.classList.remove("erro", "sucesso");
}

function mostrarErrosCampos(erros, prefixo = "") {
    Object.entries(erros).forEach(([campo, mensagem]) => {
        const span = document.getElementById(`erro-${prefixo}${campo}`);
        if (span) span.textContent = mensagem;
    });
}

const abaLogin = document.getElementById("abaLogin");
const abaCadastro = document.getElementById("abaCadastro");
const login = document.getElementById("login");
const cadastro = document.getElementById("cadastro");

abaLogin.addEventListener("click", () => {
    abaLogin.classList.add("ativa");
    abaCadastro.classList.remove("ativa");
    login.classList.add("ativa");
    cadastro.classList.remove("ativa");
    limparCadastro();
});

abaCadastro.addEventListener("click", () => {
    abaCadastro.classList.add("ativa");
    abaLogin.classList.remove("ativa");
    cadastro.classList.add("ativa");
    login.classList.remove("ativa");
    limparLogin();
});

document.getElementById("btnLogin").addEventListener("click", async () => {
    const email = document.getElementById("loginEmail").value;
    const senha = document.getElementById("loginSenha").value;
    const msg = document.getElementById("msgLogin");

    limparErros("erro-login-");
    msg.textContent = "";
    msg.classList.remove("erro", "sucesso");

    if (!email || !senha) {
        msg.textContent = "Preencha todos os campos.";
        msg.classList.add("erro");
        return;
    }

    const response = await fetch(`${API_URL}/login`, {
        method: "POST",
        credentials: "include",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, senha })
    });

    if (response.ok) {
        limparLogin()
        window.location.href = "../listaEventos/listaEventos.html";
    } else {
        const contentType = response.headers.get("content-type");

        if (contentType && contentType.includes("application/json")) {
            const erros = await response.json();
            mostrarErrosCampos(erros, "login-");
        } else {
            msg.textContent = await response.text();
            msg.classList.add("erro");
        }
    }
});

document.getElementById("btnCadastro").addEventListener("click", async () => {
    const nome = document.getElementById("cadastroNome").value;
    const email = document.getElementById("cadastroEmail").value;
    const matricula = document.getElementById("cadastroMatricula").value;
    const senha = document.getElementById("cadastroSenha").value;
    const confirmar = document.getElementById("cadastroConfirmar").value;
    const msg = document.getElementById("msgCadastro");

    limparErros("erro-");
    msg.textContent = "";
    msg.classList.remove("erro", "sucesso");

    if (!nome || !email || !matricula || !senha || !confirmar) {
        msg.textContent = "Preencha todos os campos.";
        msg.classList.add("erro");
        return;
    }

    if (senha !== confirmar) {
        const span = document.getElementById("erro-confirmarSenha");
        if (span) span.textContent = "As senhas não coincidem.";
        return;
    }

    const response = await fetch(`${API_URL}/cadastro`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome, email, senha, matricula })
    });

    if (response.status === 201) {
        msg.textContent = "Cadastro realizado com sucesso!";
        msg.classList.add("sucesso");
        limparCadastro();
    } else {
        const erros = await response.json();
        mostrarErrosCampos(erros);
    }
});

function toggleSenha(id, el) {
    const input = document.getElementById(id);
    const icon = el.querySelector("i");

    if (input.type === "password") {
        input.type = "text";
        icon.classList.replace("fa-eye", "fa-eye-slash");
    } else {
        input.type = "password";
        icon.classList.replace("fa-eye-slash", "fa-eye");
    }
}