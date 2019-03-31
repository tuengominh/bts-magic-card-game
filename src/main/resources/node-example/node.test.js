const Calculator = require('./node.js');

test('Calculator can add numbers', () => {
    const c = new Calculator();
    c.add(1);
    c.add(2);

    expect(c.value).toBe(3);
});