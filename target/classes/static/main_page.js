document.addEventListener('DOMContentLoaded', function () {
    const largeTextElements = document.querySelectorAll('.large-text');
    const smallTextElements = document.querySelectorAll('.small-text');
    const images = document.querySelectorAll('.img-custom-pic');

    // По умолчанию показываем первый текст и изображение
    document.getElementById('content1').style.display = 'block';
    document.getElementById('image1').classList.add('image-active');

    largeTextElements.forEach(element => {
        element.addEventListener('click', function () {
            const targetId = this.getAttribute('data-target');
            smallTextElements.forEach(textElement => {
                textElement.style.display = textElement.id === targetId ? 'block' : 'none';
            });
            images.forEach(image => {
                if (image.getAttribute('data-target') === targetId) {
                    image.classList.add('image-active');
                } else {
                    image.classList.remove('image-active');
                }
            });
        });
    });
});

function showBubbles(event) {
    const button = event.currentTarget;
    const bubblesContainer = document.createElement('div');
    bubblesContainer.classList.add('bubbles-container');
    button.appendChild(bubblesContainer);

    for (let i = 0; i < 10; i++) {
        const bubble = document.createElement('div');
        bubble.classList.add('bubble');
        bubble.style.left = `${Math.random() * 100}%`;
        bubble.style.width = bubble.style.height = `${Math.random() * 10 + 5}px`;
        bubblesContainer.appendChild(bubble);

        bubble.addEventListener('animationend', () => {
            bubble.remove();
        });
    }
    window.location.href = "/login";

    setTimeout(() => {
        bubblesContainer.remove();
    }, 1000);
}
