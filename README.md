### Description
TabExpand removes the default 16-character restriction for scoreboard team **prefix** and **suffix** in Minecraft 1.7.10. It increases the usable length (default: 64) and keeps both client and server behavior consistent via mixins.

The mod targets only prefix/suffix fields. Other scoreboard data (team name, display name, entries) remains unchanged to avoid unintended side effects.

---

### Features
- Expands **prefix/suffix length** (vanilla: 16 → configurable, default: 64)
- Works on both **client and server** sides
- **Configurable limits** (separate read/write control)
- Optional toggle to **enable/disable** expansion without removing the mod
- Minimal, targeted mixins (does not modify unrelated packet fields)

---

### Configuration
File: `config/TabExpand.cfg`

Options:
- `enableExpansion` (boolean)
  Enables or disables the feature.

- `prefixSuffixReadLimit` (int, default: 64)
  Max length accepted when **reading** prefix/suffix from packets.

- `prefixSuffixWriteLimit` (int, default: 64)
  Max length allowed when **writing** prefix/suffix.

Recommended range: `16–256`

---

### Compatibility
- Minecraft: **1.7.10**
- Forge-based environments (including hybrid servers like Crucible)
- Compatible with plugins/mods that use scoreboard teams (TAB, permissions plugins, etc.)

---

### Notes  
- Clients without the mod may still be limited by vanilla constraints.
- Excessively large values can cause visual issues or incompatibility with other mods/plugins.
- Designed to be safe by only modifying prefix/suffix fields.

---

### Use Cases  
- Longer rank tags (e.g., `[Admin]`, extra info)
- More detailed tab list formatting
- Servers with heavy customization of scoreboard teams

---

### Recommended Mods
- **[NecroTempus](https://github.com/CrucibleMC/NecroTempus)** (1.8+ tab rendering)

---

### Dependencies  
- **UniMixins** (required) ([Modrinth](https://modrinth.com/mod/unimixins) [CurseForge](https://www.curseforge.com/minecraft/mc-mods/unimixins))
  Provides mixin support for Minecraft 1.7.10 environments.
