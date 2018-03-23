package ru.com.rh.sp.expandedMenuCreator;


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
     * @param pos индекс возвращаемой из массива группы
     * @return группа из меню
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    Group getGroupByPosition(int pos) {
        return groups.get(pos);
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
            Group newGroup = new Group(group.name, group.icon);
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
        private int id;
        private Drawable icon;
        private ArrayList<MenuItem> items;

        Group(String name, Drawable icon) {
            this.name = name;
            id = name.hashCode();
            this.icon = icon;
            items = new ArrayList<>();
        }

        Group getCopy() {
            return new Group(this.name, this.icon);
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

        public int getId() {return id;}

        ArrayList<MenuItem> getItems() {
            return items;
        }

        Drawable getIcon() {
            return icon;
        }

        /**
         * Добавляет пункт меню
         * @param name - имя пункта меню
         * @param icon - иконка пункта меню
         * @return - добавленный экземпляр MenuItem
         */
        public MenuItem addMenuItem(String name, Drawable icon) {
            MenuItem newMenuItem = new MenuItem(name, icon);
            items.add(newMenuItem);
            return newMenuItem;
        }

        /**
         * @param id индекс возвращаемого пункта подменю
         * @return вовзращает пункт подменю
         */
        MenuItem getMenuItemByPosition(int id) {
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
        public class MenuItem  {
            private int id;
            private String name;
            private Drawable icon;

            MenuItem(String name, Drawable icon) {
                this.name = name;
                this.id = name.hashCode();
                this.icon = icon;
            }

            String getName() {
                return name;
            }

            public int getId() {return id;}

            Drawable getIcon() {
                return icon;
            }
        }
    }
}