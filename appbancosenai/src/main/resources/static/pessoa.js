const formPessoa = document.getElementById("cadastroPessoa");
const tabelaPessoa = document.getElementById("tabelaPessoas").getElementsByTagName('tbody')[0];
const btnAll = document.getElementById("listarPessoas");
const btnDelete = document.getElementById("excluir");

formPessoa.addEventListener("submit", function (event) {
    event.preventDefault(); //customização
    let formDado = new FormData(formPessoa);
    let parametros = new URLSearchParams(formDado);

    fetch("/person?"+parametros.toString(), {
        method: "POST"
    }).then(response => response.json())
        .then(data => {
            document.getElementById("id").value = data.id;
        })
});




btnAll.addEventListener("click", function () {
        fetch("/all")
            .then(response => response.json())
            .then(data => {
                tabelaPessoa.innerHTML = "";
                data.forEach(pessoa => {
                    let row = tabelaPessoa.insertRow();
                    row.insertCell(0).textContent = pessoa.id;
                    row.insertCell(1).textContent = pessoa.name;
                    row.insertCell(2).textContent = pessoa.sexo;
                })

            })
    });

btnDelete.addEventListener("click", function (){
    fetch("/person?name" + document.getElementById("nome").value, {
        method: "DELETE"
    }).then(response => response.json())
});






