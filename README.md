# Enchanting Smith

## About

- Add new Smithing Recipe format applying enchantment
    - Additional recipes can be added via json recipes!

## Supported Loader & Versions

|  Loader  | MC version | Available |
|:--------:|:----------:|:---------:|
|  Fabric  |   1.16.5   |     O     |
|          |   1.18.2   |     X     |
|          |   1.19.2   |     X     |
|          |   1.20.1   |     O     |
|  Forge   |   1.16.5   |     O     |
|          |   1.18.2   |     X     |
|          |   1.19.2   |     X     |
|          |   1.20.1   |     X     |
| NeoForge |     -      |     -     |

## Dependencies

- Fabric ... None
- Forge ... None

## Json Recipe Format

```json
{
  "type": "enchanting_smith:upgrade",
  "upgrade": {
    "item": "item id (ex; minecraft:iron_ingot)",
    "tag": "item tag id (ex; minecraft:water_buckets)",
    "_comment": "upgrade block is the same as ingredient format, so must have either \"item\" or \"tag\"!!"
  },
  "enchantment": "enchantment id (ex; minecraft:protection)"
}
```

## Credits

- Icon
    - [Original - Game-icons.net](https://game-icons.net/1x1/lorc/anvil-impact.html)
    - [License - CC BY 3.0](https://creativecommons.org/licenses/by/3.0/)