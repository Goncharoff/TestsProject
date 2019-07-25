<template>
  <div id="app">
    <vue-dropdown
      :config="config"
      @setSelectedOption="setNewSelectedOption($event);"
    ></vue-dropdown>
  </div>
</template>

<script>
import vueDropdown from "./components/vue-dropdown/vue-dropdown";

export default {
  name: "App",
  components: {
    vueDropdown
  },
  data: function() {
    return {
      config: {
        options: [
          {
            value: "option 1"
          },
          {
            value: "option 2"
          },
          {
            value: "option 3"
          }
        ],
        placeholder: "Placeholder",
        backgroundColor: "#cde4f5",
        textColor: "black",
        borderRadius: "1.5em",
        border: "1px solid gray",
        width: 180
      }
    };
  },
  methods: {
    setNewSelectedOption(selectedOption) {
      this.config.placeholder = selectedOption.value;
    }
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>