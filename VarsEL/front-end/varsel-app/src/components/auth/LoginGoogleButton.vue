<template>
  <q-btn
    flat
    outline
    class="google-btn"
  >
    <img
      class="google-icon"
      :src="GoogleLoginVector"
      alt="Google innlogging"
    />
  </q-btn>
</template>

<style lang="scss">
.google-btn {
  display: block;
  width: 100%;
  margin: 10px auto;
  max-width: max-content;
}
.google-icon {
  width: 36px;
  height: auto;
  margin: 12px;
  padding: 0;
}
</style>

<script setup>
import GoogleLoginVector from 'assets/c_icons/continue_wgoogle_light.svg'
</script>
