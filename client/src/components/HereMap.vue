<template>
    <div class="here-map">
        <div ref="map" v-bind:style="{ width: width, height: height }"></div>
    </div>
</template>

<script>
    
    // much of this code has been adapted from tutorials found on the HERE api docs
    // one such tutorial is found here: 
    // https://developer.here.com/documentation/examples/maps-js/events/position-on-mouse-click
    export default {
        name: "HereMap",
        data() {
            return {
                map: {},
                platform: {},
                geocoder: {},
                service: {},
                ui: {}
            }
        },
        props: {
            lat: String,
            lng: String,
            width: String,
            height: String,
            newAddress: String
        },
        watch: {
            newAddress(newAddr) {
//                console.log("child: " + newAddr);
                this.geocoder.geocode({ searchText: newAddr }, data => {
                    if(data.Response.View.length > 0) {
                        if(data.Response.View[0].Result.length > 0) {
                            var coords = data.Response.View[0].Result[0].Location.DisplayPosition;
                            this.map.setCenter({lat: coords.Latitude, lng: coords.Longitude });
                            // eslint-disable-next-line
                            this.map.addObject(new H.map.Marker({lat: coords.Latitude, lng: coords.Longitude }));
                            this.$emit('new-location', data.Response.View[0].Result[0].Location.Address.Label);
                            this.$emit('new-city', data.Response.View[0].Result[0].Location.Address.City);
                            this.$emit('new-coords', coords.Latitude + "," + coords.Longitude);
                        } else {
                            badLocation();
                        }
                    } else {
                        badLocation();
                    }
                }, error => {
                    console.error(error);
                    badLocation();
                });
              function badLocation() {
                  this.$emit('new-coords', "");
                  this.$emit('new-location', "That location could not be found.");
                  this.$emit('new-city', "");
              }
            }  
        },
        created() {
            // eslint-disable-next-line
            this.platform = new H.service.Platform({
                app_id: "ui9WRGl7eFkoMFuGEed3",
                apikey: "_pbd0lc2y8nctrFKjEfiXs4I3-E611GPNW7rYcmkamA"
            });
            this.geocoder = this.platform.getGeocodingService();
            this.service = this.platform.getSearchService();
        },
        methods: {
            setUpClickListener: function (map) {
              let _this = this;
              // Attach an event listener to map display
              // obtain the coordinates and display in an alert box.
              map.addEventListener('tap', function (evt) {
                var coord = map.screenToGeo(evt.currentPointer.viewportX,
                        evt.currentPointer.viewportY);
//                console.log(Math.abs(coord.lat.toFixed(4)) +
//                    ((coord.lat > 0) ? 'N' : 'S'), Math.abs(coord.lng.toFixed(4)) +
//                     ((coord.lng > 0) ? 'E' : 'W'));
                _this.mapToAddress(coord);
              });
            },
            mapToAddress: function (coords) {
                var newCoords = coords.lat + "," + coords.lng;
                this.service.reverseGeocode({
                    at: newCoords
                }, (result) => {
                    result.items.forEach((item) => {
                        this.$emit('new-location', item.address.label);
                        this.$emit('new-city', item.address.city);
                        this.$emit('new-coords', newCoords);
                        // eslint-disable-next-line
                        this.ui.addBubble(new H.ui.InfoBubble(item.position, {
                            content: item.address.label
                        }));
                    });
                }, alert);
            }
        },
        mounted() {
            var defaultLayers = this.platform.createDefaultLayers();
            // eslint-disable-next-line
            this.map = new H.Map(
                this.$refs.map,
                defaultLayers.vector.normal.map,
                {
                    zoom: 12,
                    center: { lng: this.lng, lat: this.lat },
                    pixelRatio: window.devicePixelRatio || 1
                }
            );
  
            window.addEventListener('resize', () => this.map.getViewPort().resize());
  
            // eslint-disable-next-line
            var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(this.map));
            
            // eslint-disable-next-line
            this.ui = H.ui.UI.createDefault(this.map, defaultLayers);
          
            this.setUpClickListener(this.map);
        }
    }    
</script>

<style scoped>

</style>
