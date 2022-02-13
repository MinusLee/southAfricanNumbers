function createMessage(header, htmlMessage, type) {
    const wrapperDiv = document.createElement('div');
    wrapperDiv.setAttribute('class', 'column is-half');

    const article = document.createElement('article');
    article.setAttribute('class', 'message is-small ' + type);

    const messageHeaderDiv = document.createElement('div');
    messageHeaderDiv.setAttribute('class', 'message-header');

    const p = document.createElement('p');
    p.innerText = header;

    const button = document.createElement('button');
    button.setAttribute('class', 'delete is-small');
    button.setAttribute('aria-label', 'delete');
    button.onclick = () => wrapperDiv.remove();

    const messageBodyDiv = document.createElement('div');
    messageBodyDiv.setAttribute('class', 'message-body');
    messageBodyDiv.innerHTML = htmlMessage;

    messageHeaderDiv.appendChild(p);
    messageHeaderDiv.appendChild(button);

    article.appendChild(messageHeaderDiv);
    article.appendChild(messageBodyDiv);

    wrapperDiv.appendChild(article);

    return wrapperDiv;
}