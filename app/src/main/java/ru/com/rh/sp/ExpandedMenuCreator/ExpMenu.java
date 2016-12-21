package ru.com.rh.sp.ExpandedMenuCreator;


import android.graphics.drawable.Drawable;

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

public class ExpMenu{
    private ArrayList<Group> groups;
    private Drawable groupIndicatorClose;
    private Drawable groupIndicatorOpen;


    /**
     *
     * @param close изображение закрытого индикаора группы
     * @param open изображения открытого индикатора группы
     */
    public ExpMenu(Drawable close, Drawable open) {
        groups = new ArrayList<>();
        groupIndicatorClose = close;
        groupIndicatorOpen = open;
    }

    Drawable getGroupIndicatorClose() {
        return groupIndicatorClose;
    }

    Drawable getGroupIndicatorOpen() {
        return groupIndicatorOpen;
    }

    /**
     * @param name имя новой группы
     * @return вовзращает ссылку на созданную и добавленную группу
     */
    public Group addGroup(String name, Drawable icon) {
        Group result = new Group(name, icon);
        groups.add(result);
        return result;
    }

    /**
     * @param id индекс возвращаемой из массива группы
     * @return группа из меню
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    Group getGroupById(int id) {
        return groups.get(id);
    }

    ArrayList<Group> getGroups() {
        return groups;
    }

    int size() {
        return groups.size();
    }

    ArrayList<Group> getGroupsCopy() {
        ArrayList<Group> groups = new ArrayList<>();
        for (Group group : this.groups) {
            Group newGroup = new Group(group);
            newGroup.setItems(group.items);
            groups.add(newGroup);
        }
        return groups;
    }

    /**
     * Внутренний класс Group служит для управления группами меню
     * После создания группы в {@link ExpMenu}, можно приступат к наполнению групп
     * пунктами подменю {@link MenuItem}.
     * Для этого мы вызываем метод addItem у созданной группы
     */
    public class Group {
        private String name;
        private Drawable icon;
        private ArrayList<MenuItem> items;

        Group(String name, Drawable icon) {
            this.name = name;
            this.icon = icon;
            items = new ArrayList<>();
        }
        private Group(Group group) {
            this.name = group.name;
            this.icon = group.icon;
        }

        Group getCopy() {
            return new Group(this);
        }

        void setItems(ArrayList<MenuItem> items) {
            this.items = items;
        }

        /**
         * @return вовзращает имя группы меню
         */
        String getName() {
            return name;
        }


        ArrayList<MenuItem> getItems() {
            return items;
        }

        Drawable getIcon() {
            return icon;
        }

        public void addMenuItem(String name, Drawable icon) {
            items.add(new MenuItem(name, icon));
        }

        /**
         * @param id индекс возвращаемого пункта подменю
         * @return вовзращает пункт подменю
         */
        MenuItem getMenuItemById(int id) {
            return items.get(id);
        }

        int size() {
            return items.size();
        }

        /**
         * Внутренний класс MenuItem служит для управления пунктами подменю.
         * В классе храниться следующая информация:
         * -название подменю
         * -иконка подменю
         */
        class MenuItem  {
            private String name;
            private Drawable icon;

            MenuItem(String name, Drawable icon) {
                this.name = name;
                this.icon = icon;
            }

            String getName() {
                return name;
            }

            Drawable getIcon() {
                return icon;
            }
        }
    }
}