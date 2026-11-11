window.addEventListener('load', function () {
    const width = document.documentElement.clientWidth;
    const offcanvas = document.querySelector('#offcanvasScrolling');
    if (!offcanvas) {
        return;
    }

    offcanvas.addEventListener('hide.bs.offcanvas', () => {
        const toggle = document.querySelector('.navbar-toggler[data-bs-target="#offcanvasScrolling"]');
        if (toggle) {
            toggle.classList.add('offcanvas-hidden');
        }
    });

    offcanvas.addEventListener('show.bs.offcanvas', () => {
        offcanvas.style.visibility = '';
        const toggle = document.querySelector('.navbar-toggler[data-bs-target="#offcanvasScrolling"]');
        if (toggle) {
            toggle.classList.remove('offcanvas-hidden');
        }
    });

    const toggleButton = document.querySelector('.navbar-toggler[data-bs-target="#offcanvasScrolling"]');
    if (width < 992 && toggleButton) {
        toggleButton.click();
    } else {
        offcanvas.style.visibility = '';
    }
}, false);

window.addEventListener('resize', function () {
    const width = document.documentElement.clientWidth;
    const toggleButton = document.querySelector('.navbar-toggler[data-bs-target="#offcanvasScrolling"]');
    if (!toggleButton) {
        return;
    }

    if (width >= 992 && toggleButton.classList.contains('offcanvas-hidden')) {
        toggleButton.click();
    } else if (width < 992 && !toggleButton.classList.contains('offcanvas-hidden')) {
        toggleButton.click();
    }
}, false);
