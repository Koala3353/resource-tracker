# Post-Apocalyptic RP Discord Server Bot

This bot is designed to manage resources and currency for player-run factions in your post-apocalyptic role-playing server. It helps track and manage various resources, currency, and rare items, and allows admins/game masters to modify resources based on in-game events.

## Resources

### Basic Resources

1. **Firearms**
    - **Description:** The number of firearms available to arm your soldiers or populace.
  
2. **Food**
    - **Description:** Keeps your populace fed and happy. Surplus food contributes to population growth.

3. **Electricity**
    - **Description:** Powers your metro stations and keeps your populace happy. Usage depends on the number of metro stations and population.

4. **Water**
    - **Description:** Keeps your population hydrated and can be used to generate power. Excess water contributes to power generation and population growth.
  
5. **Medical Supplies**
    - **Description:** Allows citizens to treat wounds and diseases. Surplus medical supplies can potentially generate Military Grade Rounds (currency) and contribute to population growth.

### Currency

1. **Military Grade Rounds**
    - **Description:** The currency used for buying and selling goods in the metro, forming the backbone of the economy.

### Rare Resources

1. **Machine Parts**
    - **Description:** Used for repairing vehicles and other machinery.

2. **Electronics**
    - **Description:** Essential for repairing electrical devices and are also used in cars and other machines.

3. **Radiation Equipment**
    - **Description:** Protects scavengers from surface radiation. Limited number of uses.

4. **Fuel**
    - **Description:** Powers vehicles and generators.

## Faction Management

### Initial Setup

Each faction starts with a set amount of resources. Here is an example of the initial resource list that can be customized per faction:

```plaintext
- Firearms: 100
- Food: 500
- Electricity: 300
- Water: 600
- Medical Supplies: 200
- Military Grade Rounds: 1000
- Machine Parts: 50
- Electronics: 30
- Radiation Equipment: 15
- Fuel: 100
```

### Resource Modifiers

Factions can have modifiers that affect resource generation every 24 hours. These modifiers can be positive or negative based on the faction's characteristics, strengths, and weaknesses.

### Admin/GM Commands

Admins or game masters can modify faction resources based on role-playing events. Here are the primary commands for resource management:

1. **Add Resources**
    - **Description:** Add a specified amount of resources to a faction.
    - **Command:** `=addresources <faction> <resource> <amount>`
    - **Example:** `=addresources FactionA food 100`

2. **Remove Resources**
    - **Description:** Remove a specified amount of resources from a faction.
    - **Command:** `=removeresources <faction> <resource> <amount>`
    - **Example:** `=removeresources FactionA food 50`

3. **Set Resource Modifiers**
    - **Description:** Set a resource modifier for a faction.
    - **Command:** `=setmodifier <faction> <resource> <modifier>`
    - **Example:** `=setmodifier FactionA food +10`

4. **View Faction Resources**
    - **Description:** View the current resource levels of a faction.
    - **Command:** `=viewresources <faction>`

## Usage Examples

- **Add Resources to FactionA:** `=addresources FactionA food 100`
- **Remove Resources from FactionA:** `=removeresources FactionA food 50`
- **Set Food Modifier for FactionA:** `=setmodifier FactionA food +10`
- **View Resources for FactionA:** `=viewresources FactionA`

# Pictures
![image](https://github.com/user-attachments/assets/4e08b8fc-70a3-44d7-b6e5-de6053126d85)
![image](https://github.com/user-attachments/assets/05e8ceda-b980-4115-887c-bcf1bc021221)

