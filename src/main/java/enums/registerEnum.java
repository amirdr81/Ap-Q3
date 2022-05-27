package enums;

public enum registerEnum
{
    //filePath
    filePath("src/main/java/dataBase/usersData.json");

    public final String regex;

    registerEnum(String regex)
    {
        this.regex = regex;
    }

}
