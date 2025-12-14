document.addEventListener("DOMContentLoaded", function () {

    // -------------------- PEGAR CONSULTA ID DA URL --------------------
    const urlParams = new URLSearchParams(window.location.search);
    const consultaId = urlParams.get("consultaId");

    if (!consultaId) {
        alert("Consulta não informada!");
        return;
    }

    document.getElementById("consultaIdTexto").textContent = consultaId;

    // Carrega lista ao entrar
    carregarTratamentos();


    // -------------------- BOTÃO SALVAR TRATAMENTO --------------------
    document.getElementById("btnSalvarTratamento").addEventListener("click", function () {

        const dto = {
            consultaId: consultaId,
            medicamento: document.getElementById("tMedicamento").value,
            dosagem: document.getElementById("tDosagem").value,
            vezesAoDia: Number(document.getElementById("tVezes").value),
            dias: Number(document.getElementById("tDias").value),
            dataInicio: document.getElementById("tDataInicio").value
        };

        if (!dto.dataInicio) {
            alert("Informe a data de início!");
            return;
        }

        fetch("/api/tratamento/salvar", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(dto)
        })
            .then(r => {
                if (!r.ok) throw new Error("Erro ao salvar!");
                return r.json();
            })
            .then(() => {
                limparFormulario();
                carregarTratamentos();
                alert("Tratamento salvo com sucesso!");
            })
            .catch(err => console.error(err));
    });


    // -------------------- FUNÇÃO PARA LIMPAR CAMPOS --------------------
    function limparFormulario() {
        document.getElementById("tMedicamento").value = "";
        document.getElementById("tDosagem").value = "";
        document.getElementById("tVezes").value = "";
        document.getElementById("tDias").value = "";
        document.getElementById("tDataInicio").value = "";
    }


    // -------------------- LISTAR TRATAMENTOS --------------------
    function carregarTratamentos() {

        fetch("/api/tratamento/pesquisa/" + consultaId)
            .then(r => r.json())
            .then(lista => {

                if (!lista.length) {
                    document.getElementById("tabelaTratamentos").innerHTML =
                        "<p>Nenhum tratamento encontrado.</p>";
                    return;
                }

                let html = `
                    <table class="tabela">
                        <thead>
                            <tr>
                                <th>Medicamento</th>
                                <th>Dosagem</th>
                                <th>Vezes por Dia</th>
                                <th>Dias</th>
                                <th>Medicar Até</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                `;

                lista.forEach(t => {
                    html += `
                        <tr>
                            <td>${t.nomeMedicamento}</td>
                            <td>${t.dosagem}</td>
                            <td>${t.vezesAoDia}</td>
                            <td>${t.quantidadeDias}</td>
                            <td>${t.dataFim}</td>
                            <td>
                                <button class="btn-danger" onclick="deletarTratamento(${t.id})">
                                    Excluir
                                </button>
                            </td>
                        </tr>
                    `;
                });

                html += "</tbody></table>";

                document.getElementById("tabelaTratamentos").innerHTML = html;
            });
    }


    // -------------------- EXCLUIR TRATAMENTO --------------------
    window.deletarTratamento = function (id) {
        if (!confirm("Deseja excluir este tratamento?")) return;

        fetch("/api/tratamento/delete/" + id, {method: "DELETE"})
            .then(() => carregarTratamentos());
    };

});
