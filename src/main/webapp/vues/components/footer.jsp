        </main>
    </div>

    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
    <script>
        const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
        const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

        // Tri du tableau
        document.querySelectorAll('.sortable').forEach(th => {
            let asc = true
            th.addEventListener('click', () => {
                const col = parseInt(th.dataset.col)
                const tbody = document.querySelector('#tableInterventions tbody')
                const rows = Array.from(tbody.querySelectorAll('tr'))

                rows.sort((a, b) => {
                    const aText = a.cells[col]?.textContent.trim() ?? ''
                    const bText = b.cells[col]?.textContent.trim() ?? ''

                    // Tri numérique pour la colonne ID (col 0)
                    if (col === 0) return asc ? aText - bText : bText - aText

                    // Tri alphabétique pour le reste
                    return asc
                        ? aText.localeCompare(bText, 'fr')
                        : bText.localeCompare(aText, 'fr')
                })

                // Mettre à jour les icônes
                document.querySelectorAll('.sortable i').forEach(i => {
                    i.className = 'bi bi-arrow-down-up text-secondary ms-1'
                })
                th.querySelector('i').className = asc
                    ? 'bi bi-arrow-up text-primary ms-1'
                    : 'bi bi-arrow-down text-primary ms-1'

                asc = !asc
                rows.forEach(row => tbody.appendChild(row))
            })
        })
    </script>
</body>
</html>