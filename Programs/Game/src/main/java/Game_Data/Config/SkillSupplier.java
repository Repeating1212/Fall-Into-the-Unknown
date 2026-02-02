package Game_Data.Config;

import Level.Data.Config.PlayerConfig;
import Level.Skills.Attacks.AttackArea;
import Level.Skills.Attacks.AttackSkill;
import Level.Skills.Attacks.ConeAttackArea;
import Level.Skills.Player.Dash;
import Level.Skills.Player.Defend;
import Level.Skills.Player.EmptySkill;
import Level.Skills.Skill;
import Level.View.AttackVisualize.ConeAttackVisual;

public class SkillSupplier {

    public static AttackSkill getAttack(){
        AttackArea attackArea = new ConeAttackArea(
                SkillConfig.ATTACK_RANGE,
                SkillConfig.ATTACK_AREA_ANGLE
        );
        ConeAttackVisual attackVisual = new ConeAttackVisual(
                SkillConfig.ATTACK_RANGE,
                SkillConfig.ATTACK_AREA_ANGLE,
                SkillConfig.ATTACK_ENLARGE_DURATION,
                SkillConfig.ATTACK_FADE_OUT_DURATION
        );
        AttackSkill attackSkill = new AttackSkill(
                attackVisual,
                SkillConfig.ATTACK_RIGID_TIME,
                attackArea,
                SkillConfig.ATTACK_DAMAGE,
                SkillConfig.ATTACK_COOLDOWN,
                SkillConfig.ATTACK_ENLARGE_DURATION
        );
        return attackSkill;
    }

    public static Dash getDash(){
        return new Dash(SkillConfig.DASH_COOLDOWN, SkillConfig.DASH_RANGE);
    }

    public static Defend getDefend() {
        return new Defend(SkillConfig.DEFEND_COOLDOWN, SkillConfig.DEFEND_DURATION);
    }

    public static EmptySkill getEmptySkill(){
        return new EmptySkill();
    }

    public static Skill getPlayerSkill(int skillID){
        return switch (skillID) {
            case SkillConfig.ATTACK_SKILL_ID -> SkillSupplier.getAttack();
            case SkillConfig.DASH_SKILL_ID -> SkillSupplier.getDash();
            case SkillConfig.DEFEND_SKILL_ID -> SkillSupplier.getDefend();
            default -> SkillSupplier.getEmptySkill();
        };
    }
}
