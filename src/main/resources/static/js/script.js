
$(function() {
    const element = document.getElementById('phone');
    const maskOptions = {
        mask: '+7(000)000-00-00',
        lazy: false
    };
    const mask = new IMask(element, maskOptions);
});

function hideMessages() {
    if (window.location.href.includes('logout=true')) {
        document.getElementById('logoutAlert').style.display = 'none';
    } else {
        document.getElementById('errorAlert').style.display = 'none';
    }
}

function onBack() {
    window.history.back();
}

}


