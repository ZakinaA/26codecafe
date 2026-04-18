(() => {
    'use strict'

    const getStoredTheme = () => localStorage.getItem('theme')
    const setStoredTheme = theme => localStorage.setItem('theme', theme)

    const getPreferredTheme = () => {
        const storedTheme = getStoredTheme()
        if (storedTheme) return storedTheme
        return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'
    }

    const setTheme = theme => {
        if (theme === 'auto') {
            document.documentElement.setAttribute('data-bs-theme',
                window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light')
        } else {
            document.documentElement.setAttribute('data-bs-theme', theme)
        }
    }

    const showActiveTheme = (theme) => {
        const themeSwitcher = document.querySelector('#bd-theme')
        if (!themeSwitcher) return

        // Mettre à jour l'icône active dans le bouton
        const activeBtn = document.querySelector(`[data-bs-theme-value="${theme}"]`)
        if (!activeBtn) return

        const activeIcon = activeBtn.querySelector('i')
        if (activeIcon) {
            const themeIconActive = document.querySelector('.theme-icon-active')
            if (themeIconActive) {
                // Copier les classes de l'icône active
                themeIconActive.className = activeIcon.className
                        .replace('me-2', '')
                        .replace('opacity-50', '')
                    + ' theme-icon-active'
            }
        }

        // Mettre à jour les états actifs
        document.querySelectorAll('[data-bs-theme-value]').forEach(el => {
            const isActive = el.getAttribute('data-bs-theme-value') === theme
            el.classList.toggle('active', isActive)
            el.setAttribute('aria-pressed', isActive.toString())
            const check = el.querySelector('.bi-check2')
            if (check) check.classList.toggle('d-none', !isActive)
        })
    }

    // Appliquer le thème immédiatement (avant le DOM, évite le flash)
    setTheme(getPreferredTheme())

    // Attendre le DOM pour manipuler les éléments
    document.addEventListener('DOMContentLoaded', () => {
        showActiveTheme(getPreferredTheme())

        document.querySelectorAll('[data-bs-theme-value]').forEach(toggle => {
            toggle.addEventListener('click', () => {
                const theme = toggle.getAttribute('data-bs-theme-value')
                setStoredTheme(theme)
                setTheme(theme)
                showActiveTheme(theme)
            })
        })

        window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', () => {
            if (!getStoredTheme()) setTheme('auto')
        })
    })
})()