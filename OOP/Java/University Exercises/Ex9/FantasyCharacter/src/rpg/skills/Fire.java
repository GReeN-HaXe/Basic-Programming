package rpg.skills;

import rpg.characters.RpgCharacter;

public class Fire extends Skills {

    public Fire(RpgCharacter owner){
        super("Fire", owner, 7);
    }

    @Override
    public void use(RpgCharacter enemy) {
        enemy.receiveMagicDamage(20);
    }
}