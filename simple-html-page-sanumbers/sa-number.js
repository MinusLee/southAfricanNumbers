const createMessageFunction = createMessage;

const correctNumberRegex = /^(27)(83)([0-9]{7})$/;
const modifiableNumberRegex = /^(26|28|17|37|27)(82|84|73|93|83)([0-9]{7})$/

const parseText = () => {
    const primaryInput = document.getElementById('primary-input');
    const inputText = primaryInput.value;
    const whiteSpaceRemoved = inputText.split(' ').join('');

    const correctMatch = whiteSpaceRemoved.match(correctNumberRegex);

    if (correctMatch === null) {
        const modifiableMatch = whiteSpaceRemoved.match(modifiableNumberRegex);
        return {
            isCorrect: false,
            number: modifiableMatch === null ? inputText : modifiableMatch[1] + ' ' + modifiableMatch[2] + ' ' + modifiableMatch[3],
            modifiedNumber: modifiableMatch === null ? null : '27 83 ' + modifiableMatch[3]
        }
    }

    return {
        isCorrect: true,
        number: correctMatch[1] + ' ' + correctMatch[2] + ' ' + correctMatch[3],
        modifiedNumber: null
    }
}

const checkDifference = (string1, string2) => {
    const indexSet = new Set();

    for (let i = 0; i < string1.length; i++) {
        if (string1.charAt(i) !== string2.charAt(i)){
            indexSet.add(i);
        }
    }

    return indexSet;
}

const setNumber = (string, indexSet) => {
    let HTMLString = "";

    for (let i = 0; i < string.length; i++) {
        if (indexSet.has(i)) {
            HTMLString += "<strong>" + string.charAt(i) + "</strong>"
        }
        else {
            HTMLString += string.charAt(i);
        }
    }

    return HTMLString;
}

const appendMessage = (info) => {
    const messageParentDiv = document.getElementById('starting-point');

    if (info.isCorrect === true) {
        messageParentDiv.appendChild(
            createMessageFunction(
                'Number is correct', 
                'Your number ' + info.number + ' is well formed', 
                'is-success'));
    }

    else if (info.modifiedNumber === null) {
        messageParentDiv.appendChild(
            createMessageFunction(
                'Number is wrong', 
                'Your number ' + info.number + ' is not well formed, you should digit a number with the following format: 27 83 1234567', 
                'is-danger'));
    }

    else {
        const indexSet = checkDifference(info.number, info.modifiedNumber);
        const originalNumberHTMLString = setNumber(info.number, indexSet);
        const modifiedNumberHTMLString = setNumber(info.modifiedNumber, indexSet);

        messageParentDiv.appendChild(
            createMessageFunction(
                'Number has been modified', 
                'Number has been corrected: your number was ' + originalNumberHTMLString + ', now is ' + modifiedNumberHTMLString, 
                'is-warning'));
    }
}

function main() {
    const bt = document.getElementById('ghost-button');
    bt.onclick = () => {
        const info = parseText();
        appendMessage(info);
    };

    const btClear = document.getElementById('ghost-button-clear');
    btClear.onclick = () => document.getElementById('starting-point').innerHTML = '';
}

window.onload = main;