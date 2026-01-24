const API_URL = "http://localhost:8080/api/auth";

/* ================= UTIL ================= */

function limparErrosCampos(prefixo = "") {
    document
        .querySelectorAll(`[id^="erro-${prefixo}"]`)
        .forEach(el => el.textContent = "");
}

function mostrarErrosCampos(erros, prefixo = "") {
    Object.entries(erros).forEach(([campo, mensagem]) => {
        const span = document.getElementById(`erro-${prefixo}${campo}`);
        if (span) span.textContent = mensagem;
    });
}

function limparLogin() {
    document.getElementById("loginEmail").value = "";
    document.getElementById("loginSenha").value = "";
    document.getElementById("msgLogin").textContent = "";
    document.getElementById("msgLogin").classList.remove("erro", "sucesso");
    limparErrosCampos("login-");
}

function limparCadastro() {
    document.getElementById("cadastroNome").value = "";
    document.getElementById("cadastroEmail").value = "";
    document.getElementById("cadastroMatricula").value = "";
    document.getElementById("cadastroSenha").value = "";
    document.getElementById("cadastroConfirmar").value = "";
    document.getElementById("msgCadastro").textContent = "";
    document.getElementById("msgCadastro").classList.remove("erro", "sucesso");
}

/* ================= ABAS ================= */

document.getElementById("abaLogin").addEventListener("click", () => {
    abaLogin.classList.add("ativa");
    abaCadastro.classList.remove("ativa");
    login.classList.add("ativa");
    cadastro.classList.remove("ativa");
    limparCadastro();
    limparErrosCampos();
});

document.getElementById("abaCadastro").addEventListener("click", () => {
    abaCadastro.classList.add("ativa");
    abaLogin.classList.remove("ativa");
    cadastro.classList.add("ativa");
    login.classList.remove("ativa");
    limparLogin(); 
});

/* ================= LOGIN ================= */

document.getElementById("btnLogin").addEventListener("click", async () => {
    const email = document.getElementById("loginEmail").value;
    const senha = document.getElementById("loginSenha").value;
    const msg = document.getElementById("msgLogin");

    limparErrosCampos("login-");
    msg.textContent = "";

    if (!email || !senha) {
        msg.textContent = "Preencha todos os campos.";
        msg.classList.add("erro");
        return;
    }

    const response = await fetch(`${API_URL}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, senha })
    });

    if (response.ok) {
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

/* ================= CADASTRO ================= */

document.getElementById("btnCadastro").addEventListener("click", async () => {
    const nome = document.getElementById("cadastroNome").value;
    const email = document.getElementById("cadastroEmail").value;
    const matricula = document.getElementById("cadastroMatricula").value;
    const senha = document.getElementById("cadastroSenha").value;
    const confirmar = document.getElementById("cadastroConfirmar").value;
    const msg = document.getElementById("msgCadastro");

    limparErrosCampos();
    msg.textContent = "";

    if (!nome || !email || !matricula || !senha || !confirmar) {
        msg.textContent = "Preencha todos os campos.";
        msg.classList.add("erro");
        return;
    }

    if (senha !== confirmar) {
        document.getElementById("erro-confirmarSenha").textContent =
            "As senhas não coincidem.";
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

        limparCadastro()
    } else {
        const erros = await response.json();
        mostrarErrosCampos(erros);
    }
});

/* ================= SENHA ================= */

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
