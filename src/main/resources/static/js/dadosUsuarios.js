const totalAdmins = document.getElementById('totalAdmins').value;  // Número total de administradores
const totalClients = document.getElementById('totalClients').value; // Número total de clientes
// Renderizando o gráfico
const ctx = document.getElementById('userChart').getContext('2d');
const userChart = new Chart(ctx, {
    type: 'pie', // Tipo de gráfico: Pizza
    data: {
        labels: ['Administradores', 'Clientes'], // Os perfis de usuários
        datasets: [{
            label: 'Usuários por Perfil',
            data: [totalAdmins, totalClients], // Valores correspondentes
            backgroundColor: [
                'rgba(54, 162, 235, 0.7)', // Azul para Admins
                'rgba(255, 206, 86, 0.7)', // Amarelo para Clientes
            ],
            borderColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            tooltip: {
                enabled: true
            }
        }
    }
});
