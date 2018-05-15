package rpg;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import rpg.characters.Mage;
import rpg.characters.RpgCharacter;
import rpg.characters.Warrior;
import rpg.items.Armor;
import rpg.items.Sword;
import rpg.items.Wand;
import rpg.skills.Fire;
import rpg.skills.PowerStrike;
import rpg.skills.Skills;

import static org.junit.Assert.*;


public class TestChar{

    static RpgCharacter testWarrior;
    static RpgCharacter testWarrior2;
    static RpgCharacter testMage;

    /**
        testWarrior.setItem(new Sword());
        testWarrior.setItem(new Armor());
        testWarrior.setSkill(new PowerStrike(testWarrior));
        testMage.setItem(new Wand());
        testMage.setSkill(new Fire(testMage));
     **/

    @BeforeClass
    public static void initChar(){
        testWarrior = new Warrior(20, 10, 10, 10);
        testWarrior2 = new Warrior(30, 5, 10, 10);
        testMage = new Mage(15,15,2,8);
    }

    @Test
    public void testGetDefense(){
        assertEquals(10, testWarrior.getDefense());
        assertEquals(8, testMage.getDefense());
        testWarrior.setItem(new Armor());
        assertEquals(13, testWarrior.getDefense());
    }

    @Test
    public void testReceiveNormalDamage(){
        testMage.receiveNormalDamage(testWarrior.getAttack());
        assertEquals(13 , testMage.getCurrentHp());
        testWarrior.setItem(new Sword());
        testMage.receiveNormalDamage(testWarrior.getAttack());
        assertEquals(0 , testMage.getCurrentHp());
        assertFalse(testMage.isAlive());
    }

    @Test
    public void testReceiveMagicDamage(){
        testMage.setItem(new Wand());
        testMage.setSkill(new Fire(testMage));
        testMage.useSkill(testWarrior);
        assertEquals(0, testWarrior.getCurrentHp());
        assertFalse(testWarrior.isAlive());
    }

    @Test
    public void testNormalAttack(){
        System.out.println(testWarrior.getAttack());
        System.out.println(testMage.getDefense());
        System.out.println(testMage.getCurrentHp());
        testWarrior.normalAttack(testMage);
        assertEquals(13, testMage.getCurrentHp());
        while(testMage.getCurrentHp() > 0) {
            testWarrior.normalAttack(testMage);
        }
        System.out.println(testMage.getCurrentHp());
        assertFalse(testMage.isAlive());
    }

    @Test
    public void testUseSkill() {
        testMage.useSkill(testWarrior);
        testMage.setSkill(new Fire(testMage));
        testMage.useSkill(testWarrior);
        assertEquals(8, testMage.getCurrentMp());
        assertEquals(0, testWarrior.getCurrentHp());
        assertFalse(testWarrior.isAlive());
        testMage.useSkill(testWarrior);
        testMage.useSkill(testWarrior2);
        testMage.useSkill(testWarrior2);
    }

    @Test
    public void testUseSkill2() {
        testWarrior.setSkill(new PowerStrike(testWarrior));
        System.out.println(testWarrior.getAttack());
        testWarrior.useSkill(testMage);
        assertEquals(3, testMage.getCurrentHp());
    }

    @Test
    public void testGetCharacterStats(){
        assertTrue(testMage.getCharacterStats().equals("Class: Mage HP: 15 MP: 15 At: 2 Def: 8 Item:  _  Skill:  _"));
        assertTrue(testWarrior.getCharacterStats().equals("Class: Warrior HP: 20 MP: 10 At: 10 Def: 10 Item:  _  Skill:  _"));
    }

}
