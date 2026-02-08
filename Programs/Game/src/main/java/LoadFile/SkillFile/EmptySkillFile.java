package LoadFile.SkillFile;

public class EmptySkillFile implements SkillFile{

    // Handle null condition

    public void upgrade(int upgradeID){
        //empty
    }

    public int getUpgrade(int upgradeID){
        return 0;
    }
}
