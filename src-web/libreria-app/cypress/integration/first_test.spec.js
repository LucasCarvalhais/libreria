describe('first test', () => {
    it('successful access page', () => {
        cy.visit("/");
        cy.contains("Bienvenido");
    })
})