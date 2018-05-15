package rpg.skills;

import rpg.characters.RpgCharacter;

public class PowerStrike extends Skills {

    public PowerStrike(RpgCharacter owner) {
        super("PowerStrike", owner, 10);
    }

    @Override
    public void use(RpgCharacter enemy) {
        enemy.receiveNormalDamage(getRpgCharacter().getAttack() * 2);
    }
}
