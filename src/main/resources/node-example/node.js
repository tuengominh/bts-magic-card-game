console.log("Hello world");

class Calculator {
    constructor() {
        this.value = 0;
    }

    add(amount){
        this.value += amount;
    }
}
module.exports = Calculator;