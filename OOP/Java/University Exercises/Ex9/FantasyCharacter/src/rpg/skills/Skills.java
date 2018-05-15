package rpg.skills;

import rpg.characters.RpgCharacter;
import rpg.items.Item;

public abstract class Skills {

    private String name;
    private RpgCharacter rpgCharacter;
    private int mpCosts;

    public Skills(String name, RpgCharacter rpgCharacter, int mpCosts) {
        this.name = name;
        this.rpgCharacter = rpgCharacter;
        this.mpCosts = mpCosts;
    }

    public String getName() {
        return name;
    }

    public RpgCharacter getRpgCharacter() {
        return rpgCharacter;
    }

    public int getMpCosts() {
        return mpCosts;
    }

    public abstract void use(RpgCharacter enemy);

    public static Skills[] getAllSkills(RpgCharacter rpgChar) {
        Item item = rpgChar.getItem();
        if(item != null) {
            if(rpgChar.getItem().getName().equals("Wand") &&
                    rpgChar.getRpgClass().equals("Mage")) {
                return new Skills[] {new Fire(rpgChar), null};
            }
            if(rpgChar.getItem().getName().equals("Sword") &&
                    rpgChar.getRpgClass().equals("Warrior")) {
                return new Skills[] {new PowerStrike(rpgChar)};
            }
        }
        return new Skills[] {null};
    }




}
