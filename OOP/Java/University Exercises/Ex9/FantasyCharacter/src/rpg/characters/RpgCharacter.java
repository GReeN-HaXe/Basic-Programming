package rpg.characters; // Package for the character related Classes

import rpg.items.Item; // Used for the item variable of class Item
import rpg.skills.Skills; // Used for the skill variable of class Skills


/**
 *
 * Class that represents a character in a RPG style game where
 * characters fight each other.
 * It defines the characters class, health points, magic points,
 * base attack and base defense.
 *
 *
 */
public abstract class RpgCharacter implements ICharacter {

    /**
     *
     * Implements the ICharacter interface which declares methods for
     * applying/receiving damage and retrieving total attack/defense
     * values
     */

    String rpgClass; // the character class
    private int maxHp; // maximum health points
    private int currentHp; // current health points, suffer changes from attacks
    private int maxMp; // maximum magic points
    private int currentMp; // current magic points, suffer changes from skill usage
    private int baseAttackValue;
    private int defenseValue;
    private boolean alive; // true if character is alive
    private Item item = null; // the item the character is holding
    private Skills skill; // the skill the character is holding


    /**
     * Constructor for the creation of character objects
     *
     * @param rpgClass
     * @param hp
     * @param mp
     * @param attack
     * @param defense
     */
    public RpgCharacter(String rpgClass, int hp, int mp, int attack, int defense) {
        this.rpgClass = rpgClass;
        this.maxHp = hp;
        this.currentHp = hp;
        this.maxMp = mp;
        this.currentMp = mp;
        this.baseAttackValue = attack;
        this.defenseValue = defense;
        this.alive = true;
    }

    public String getRpgClass() {
        return rpgClass;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public int getCurrentMp() {
        return currentMp;
    }

    public int getBaseAttackValue() {
        return baseAttackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public boolean isAlive() {
        return alive;
    }

    public Item getItem() {
        return item;
    }

    public Skills getSkill() {
        return skill;
    }

    public void setItem(Item charItem) {
        this.item = charItem;
    }

    public void setSkill(Skills charSkill) {
        this.skill = charSkill;
    }

    @Override
    public int getDefense() {
        if (item == null) {
            return getDefenseValue();
        } else {
            return getDefenseValue() + item.getDefenseValue();
        }
    }

    @Override
    public int getAttack() {
        if (item == null) {
            return getBaseAttackValue();
        } else {
            return getBaseAttackValue() + item.getAttackValue();
        }
    }

    @Override
    public void receiveNormalDamage(int normalDamage) {
        if(currentHp > 0) {
            currentHp -= normalDamage - defenseValue;
            System.out.println("Character is under physical attack");
        }
        if(currentHp <= 0) {
            currentHp = 0;
            this.alive = false;
            System.out.println("Character is dead");
        }
        if(currentHp > maxHp) {
            System.out.println("maxHp IS maxHP, can't get any higher!");
        }
    }

    @Override
    public void receiveMagicDamage(int magicDamage) {
        if(currentHp > 0){
            currentHp -= magicDamage;
            System.out.println("Character is under magic attack");
        }
        if(currentHp <= 0) {
            currentHp = 0;
            this.alive = false;
            System.out.println("Character is dead");
        }
        if(currentHp > maxHp) {
            System.out.println("maxHp IS maxHP, can't get any higher!");
        }
    }

    @Override
    public void normalAttack(RpgCharacter enemy) {
        if(enemy.isAlive()) enemy.receiveNormalDamage(getAttack());
        else System.out.println("Already dead");
    }

    @Override
    public void useSkill(RpgCharacter enemy) {
        if(getSkill() != null) {
            if(enemy.isAlive()) {
                if (getCurrentMp() >= skill.getMpCosts()) {
                    currentMp -= skill.getMpCosts();
                    skill.use(enemy);
                } else System.out.println("Not enough Mp to use chosen skill");
            } else System.out.println("Enemy already dead");
        } else System.out.println("Character has not learned any skill");
    }


    public String getCharacterStats() {
        StringBuilder sb = new StringBuilder(80);
        sb.append("Class: ");
        sb.append(getRpgClass());
        sb.append(" HP: ");
        sb.append(getMaxHp());
        sb.append(" MP: ");
        sb.append(getMaxMp());
        sb.append(" At: ");
        sb.append(getBaseAttackValue());
        sb.append(" Def: ");
        sb.append(getDefenseValue());
        sb.append(" Item: ");
        if(item != null) sb.append(getItem().getName());
        else sb.append(" _ ");
        sb.append(" Skill: ");
        if(skill != null) sb.append(getSkill().getName());
        else sb.append(" _");
        return sb.toString();
    }
}
