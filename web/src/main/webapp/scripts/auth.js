const switchers = [...document.querySelectorAll('.switcher')]

switchers.forEach(item => {
    item.addEventListener('click', function() {
        switchers.forEach(item => item.parentElement.classList.remove('is-active'))
        this.parentElement.classList.add('is-active')
        const $elem = document.querySelector('.section-title');
        if(this.innerText == 'LOGIN'){
            $elem.textContent = 'Sign in with email';
        }
        else{
            $elem.textContent = 'Create account!';
        }
    })

})