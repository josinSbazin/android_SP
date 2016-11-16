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

    /**
     * @param id индекс возвращаемой из массива группы
     * @return группа из меню
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public Group getGroupById(int id) {
        return groups.get(id);
    }

    public int size() {
        return groups.size();
    }

    /**
     * Внутренний класс Group служит для управления группами меню
     * После создания группы в {@link ExpMenu}, можно приступат к наполнению групп
     * пунктами подменю {@link MenuItem}.
     * Для этого мы вызываем метод addItem у созданной группы
     */
    public class Group {
        private String name;
        private ArrayList<MenuItem> items;

        Group(String name) {
            this.name = name;
            items = new ArrayList<>();
        }

        /**
         * @return вовзращает имя группы меню
         */
        public String getName() {
            return name;
        }

        /**
         * @param id индекс возвращаемого пункта подменю
         * @return вовзращает пункт подменю
         */
        public MenuItem getMenuItemById(int id) {
            return items.get(id);
        }

        public int size() {
            return items.size();
        }

        /**
         * Внутренний класс MenuItem служит для управления пунктами подменю.
         * В классе храниться следующая информация:
         * -название подменю
         * -иконка подменю
         */
        private class MenuItem  {
            private String name;
            private Drawable icon;

            public MenuItem(String name, Drawable icon) {
                this.name = name;
                this.icon = icon;
            }

            public String getName() {
                return name;
            }

            public Drawable getIcon() {
                return icon;
            }
        }
    }
}