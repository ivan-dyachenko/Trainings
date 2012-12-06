describe("Bowling", function(){

    var game;

    beforeEach(function () {
        game = new Bowling();
    });

    it("exists", function(){
        expect(game).toBeDefined();
    });

    it("should score zero on gutter game", function () {
        rollMany(20, 0);
        expect(game.score()).toBe(0);
    });

    it("should score twenty when all one", function () {
        rollMany(20, 1);
        expect(game.score()).toBe(20);
    });

    it("should calculate a spare correctly", function () {
        game.roll(5);
        game.roll(5);
        game.roll(3);
        rollMany(17, 0);
        expect(game.score()).toBe(16);
    });

    it("should calculate a strike correctly", function () {
        game.roll(10);
        game.roll(3);
        game.roll(4);
        rollMany(16, 0);
        expect(game.score()).toBe(24);
    });

    it("should calculate 300 for a perfect game", function () {
        rollMany(12, 10);
        expect(game.score()).toBe(300);
    });

    function rollMany(n, pins) {
        for (var i = 0; i < n; i++) {
            game.roll(pins);
        }
    }
});


