package com.weather.weatherapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather", uniqueConstraints = @UniqueConstraint(columnNames = "datetime_utc"))
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datetime_utc")
    private LocalDateTime datetimeUtc;

    @Column(name = "conds")
    private String condition;

    @Column(name = "dewptm")
    private Integer dewPoint;

    @Column(name = "fog")
    private Integer fog;

    @Column(name = "hail")
    private Integer hail;

    @Column(name = "heatindexm")
    private Integer heatIndex;

    @Column(name = "hum")
    private Integer humidity;

    @Column(name = "precipm")
    private Integer precipitation;

    @Column(name = "pressurem")
    private Integer pressure;

    @Column(name = "rain")
    private Integer rain;

    @Column(name = "snow")
    private Integer snow;

    @Column(name = "tempm")
    private Integer temperature;

    @Column(name = "thunder")
    private Integer thunder;

    @Column(name = "tornado")
    private Integer tornado;

    @Column(name = "vism")
    private Double visibility;

    @Column(name = "wdird")
    private Integer windDirectionDegrees;

    @Column(name = "wdire")
    private String windDirection;

    @Column(name = "wgustm")
    private Double windGust;

    @Column(name = "windchillm")
    private Integer windChill;

    @Column(name = "wspdm")
    private Double windSpeed;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatetimeUtc() {
        return datetimeUtc;
    }

    public void setDatetimeUtc(LocalDateTime datetimeUtc) {
        this.datetimeUtc = datetimeUtc;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Integer dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Integer getFog() {
        return fog;
    }

    public void setFog(Integer fog) {
        this.fog = fog;
    }

    public Integer getHail() {
        return hail;
    }

    public void setHail(Integer hail) {
        this.hail = hail;
    }

    public Integer getHeatIndex() {
        return heatIndex;
    }

    public void setHeatIndex(Integer heatIndex) {
        this.heatIndex = heatIndex;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Integer precipitation) {
        this.precipitation = precipitation;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getRain() {
        return rain;
    }

    public void setRain(Integer rain) {
        this.rain = rain;
    }

    public Integer getSnow() {
        return snow;
    }

    public void setSnow(Integer snow) {
        this.snow = snow;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getThunder() {
        return thunder;
    }

    public void setThunder(Integer thunder) {
        this.thunder = thunder;
    }

    public Integer getTornado() {
        return tornado;
    }

    public void setTornado(Integer tornado) {
        this.tornado = tornado;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public Integer getWindDirectionDegrees() {
        return windDirectionDegrees;
    }

    public void setWindDirectionDegrees(Integer windDirectionDegrees) {
        this.windDirectionDegrees = windDirectionDegrees;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    public Integer getWindChill() {
        return windChill;
    }

    public void setWindChill(Integer windChill) {
        this.windChill = windChill;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
}