# PlaceholderAPI

# Permissions

- placeholderapi.list
- placeholderapi.download
- placeholderapi.remove

# Usage
- get message with placeholders
  - ```PlaceholderAPI.translatePlaceholdersToString(Player?, String)```

- create custom placeholders
    - 1: Create your placeholder group
    - ***HINT:*** ```object <ClassName> : PlaceholderGroup ```
    - 2: Add placeholders
    - ***HINT:***  ```object <ClassName> : Placeholder```
    - 3: add the placeholder group to the extension
    - ***HINT:*** ```PlaceholderAPI.setPlaceholders(PlaceholderGroup)```

# Command usage
- /placeholderapi or /papi
    - list
    - download <name>
    - remove <name>

# Default placeholders

The only thing you have todo is downloading the placeholder in game.
  
- Player
    - %player_allow_flight%
    - %player_fly_speed%
    - %player_food_level%
    - %player_gamemode%
    - %player_health_level%
    - %player_health_max%
    - %player_name%
    - %player_saturation_level%
    - %player_xp_amount%
    - %player_xp_level%
- PlayerItemstack
    - %playeritemstack_armor_boots_material%
    - %playeritemstack_armor_boots_name%
    - %playeritemstack_armor_leggings_material%
    - %playeritemstack_armor_leggings_name%
    - %playeritemstack_armor_chestplate_material%
    - %playeritemstack_armor_chestplate_name%
    - %playeritemstack_armor_helmet_material%
    - %playeritemstack_armor_helmet_name%
    - %playeritemstack_item_hand_material%
    - %playeritemstack_item_hand_name%
- Server
    - %server_name%
    - %server_online%
    - %server_online_world%
    - %server_tps%
    - %server_version%
