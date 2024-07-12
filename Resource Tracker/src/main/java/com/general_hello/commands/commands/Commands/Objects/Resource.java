package com.general_hello.commands.commands.Commands.Objects;

import com.general_hello.commands.commands.Commands.Items.Initializer;
import com.general_hello.commands.commands.Commands.RpgUser.ResourcesDataUtils;
import com.general_hello.commands.commands.Commands.Types.Rarity;

import java.util.LinkedList;

public class Resource extends Objects {
    public Resource(Rarity rarity, String name, String emoji, String description, String category) {
        super(name, rarity, emoji, description, category);
        String[] split = name.split("\\s+");
        Initializer.resourcesToId.put(ResourcesDataUtils.filter(split[0]), this);
        Initializer.allItems.put(ResourcesDataUtils.filter(name), this);
        Initializer.allItems.put(ResourcesDataUtils.filter(split[0]), this);
        Initializer.resourcesToId.put(name.toLowerCase().replace("'", "").replaceAll("\\s+", ""), this);
        Initializer.resources.add(this);
        Initializer.allNames.add(name);
        LinkedList<String> resourcesInCategory = Initializer.categoryToResources.getOrDefault(category, new LinkedList<>());
        resourcesInCategory.add(ResourcesDataUtils.filter(name));
        Initializer.categoryToResources.put(category, resourcesInCategory);
    }
}
