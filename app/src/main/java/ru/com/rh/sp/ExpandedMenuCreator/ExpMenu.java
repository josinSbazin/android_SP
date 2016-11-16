package ru.com.rh.sp.ExpandedMenuCreator;


import java.util.ArrayList;

/**
 * Класс для создания меню с выпадающим подменю.
 * Меню создется в следующем порядке:
 * 1. Создается само меню оператором 'new'
 * 2. Создается верхняя иерархия меню методами 'addGroup', 'setGroup'.
 * 3. Создается иерархия подменю, вызовом методов созданных групп (см. {@link Group}
 * Созданое меню прекрасно послужит для нашего адаптера
 *
 * При необходимости дополнительных функций - раскомментируйте нужные методы
 */

public class ExpMenu {
    private ArrayList<Group> groups;

    public ExpMenu () {
        groups = new ArrayList<>();
    }

    /**
     * @param name имя новой группы
     * @return вовзращает ссылку на созданную и добавленную группу
     */
    public Group addGroup(String name) {
        Group result = new Group(name);
        groups.add(result);
        return result;
    }

//    /**
//     * @param names имена новых групп через запятую ("name1", "name2", "name2", ...)
//     * @return true, if group added
//     */
//    public boolean addSeveralGroups(String... names) {
//        for (String name : names) addGroup(name);
//        return true;
//    }

//    /**
//     * @param id индекс вставляемой группы
//     * @param name имя новой группы
//     * @return возвращает ссылку на созданную и добавленную группу
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */
//    public Group setGroup(int id, String name) {
//        Group result = new Group(name);
//        groups.set(id, result);
//        return result;
//    }

//    /**
//     * @param id индекс возвращаемой из массива группы
//     * @return группа из меню
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */
//    public Group getGroupById(int id) {
//        return groups.get(id);
//    }

//    /**
//     * Возвращает первую группу {@link Group} с искомым именем
//     * если группа с введенным именем не найдена - возвращает null
//     *
//     * @param name имя возвращаемой из массива группы
//     * @return группа из меню
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */

//    public Group getGroupByName(String name) {
//        for (Group group : groups) {
//            if (group.name.equals(name)) return group;
//        }
//        return null;
//    }

    /**
     * Внутренний класс Group служит для управления группами меню
     * После создания группы в {@link ExpMenu}, можно приступат к наполнению групп
     * пунктами подменю {@link MenuItem}.
     * Для этого мы вызываем метод addItem у созданной группы
     */
    public class Group {
        private String name;

        Group(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        private class MenuItem  {

        }
    }
}
