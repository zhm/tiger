# TIGER Review Plugin

This plugin adds a keyboard shortcut (SHIFT+R) to JOSM for removing the tiger:reviewed=yes tag on selected ways.
It is intended as a quick way to nuke the `tiger:reviewed` tag while doing a lot of TIGER fixup.

To build the plugin, you need the JOSM source first.

# Build Instructions
* Get the JOSM source `svn co http://svn.openstreetmap.org/applications/editors/josm josm`
* navigate to the `josm/plugins/` directory
* clone this repo to the `josm/plugins` directory
* navigate to the `core/` directory in the root of the JOSM source
* build JOSM core using `ant clean && ant dist`
* navigate back into `josm/plugins/tiger`
* build the plugin using `ant clean && ant dist && ant install`
* if it worked, you should have `tiger.jar` in `~/.josm/plugins`
* run JOSM and it should show up in the plugins tab, enable it and restart JOSM
* FIX ALL THE ROADS!

Note: this plugin probably barely works and has bugs since I have no idea what I'm doing.
