/* =====================================================
   API URLS
===================================================== */

const STATION_API =
    "/api/ev/stations";

const CHARGER_API =
    "/api/ev/chargers";

const SESSION_API =
    "/api/ev/sessions";


/* =====================================================
   PAGE NAVIGATION
===================================================== */

function scrollToSection(id) {

    const section =
        document.getElementById(id);

    if (section) {
        section.scrollIntoView({
            behavior: "smooth"
        });
    }
}


/* =====================================================
   DASHBOARD
===================================================== */

async function loadDashboard() {

    await loadStations();
    await loadChargers();
    await loadSessions();
}


/* =====================================================
   STATIONS
===================================================== */

async function loadStations() {

    try {

        const response =
            await fetch(STATION_API);

        if (!response.ok) {
            throw new Error(
                "Failed to load stations"
            );
        }

        const stations =
            await response.json();

        displayStations(stations);

        document.getElementById(
            "stationCount"
        ).innerText = stations.length;

        document.getElementById(
            "reportStations"
        ).innerText = stations.length;

        loadStationDropdown(stations);

    } catch (error) {

        console.error(error);

        document.getElementById(
            "stationList"
        ).innerHTML = `
            <p class="loading">
                Unable to load stations.
                Check the Spring Boot backend.
            </p>
        `;
    }
}


/* =====================================================
   DISPLAY STATIONS
===================================================== */

function displayStations(stations) {

    const container =
        document.getElementById(
            "stationList"
        );

    container.innerHTML = "";

    if (!stations || stations.length === 0) {

        container.innerHTML = `
            <p class="loading">
                No charging stations available.
            </p>
        `;

        return;
    }

    stations.forEach(station => {

        const card =
            document.createElement("div");

        card.className =
            "station-card";

        card.innerHTML = `

            <h3>
                ⚡ ${escapeHtml(
            station.stationName || "Station"
        )}
            </h3>

            <p>
                📍 ${escapeHtml(
            station.location || "Not available"
        )}
            </p>

            <p>
                🏙 ${escapeHtml(
            station.city || "Not available"
        )}
            </p>

            <p>
                Status:
                <span class="status ${getStatusClass(station.status)}">
                    ${escapeHtml(
            station.status || "UNKNOWN"
        )}
                </span>
            </p>

            <p>
                ⚡ Power:
                ${station.totalPowerCapacity ?? 0}
            </p>

            <button
                class="delete-button"
                onclick="deleteStation(${station.id})">

                Delete

            </button>
        `;

        container.appendChild(card);
    });
}


/* =====================================================
   STATION DROPDOWN
===================================================== */

function loadStationDropdown(stations) {

    const dropdown =
        document.getElementById(
            "chargerStation"
        );

    dropdown.innerHTML = `
        <option value="">
            Select Station
        </option>
    `;

    stations.forEach(station => {

        const option =
            document.createElement("option");

        option.value =
            station.id;

        option.textContent =
            `${station.stationName} - ${station.city}`;

        dropdown.appendChild(option);
    });
}


/* =====================================================
   CREATE STATION
===================================================== */

async function createStation() {

    const stationName =
        document.getElementById(
            "stationName"
        ).value.trim();

    const location =
        document.getElementById(
            "location"
        ).value.trim();

    const city =
        document.getElementById(
            "city"
        ).value.trim();

    const status =
        document.getElementById(
            "stationStatus"
        ).value;

    const power =
        document.getElementById(
            "power"
        ).value;


    if (
        !stationName ||
        !location ||
        !city ||
        !power
    ) {

        alert(
            "Please fill all required fields."
        );

        return;
    }


    const station = {

        stationName:
        stationName,

        location:
        location,

        city:
        city,

        status:
        status,

        totalPowerCapacity:
            Number(power)
    };


    try {

        const response =
            await fetch(
                STATION_API,
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(station)
                }
            );


        if (!response.ok) {

            throw new Error(
                "Failed to create station"
            );
        }


        alert(
            "Charging station added successfully!"
        );


        document.getElementById(
            "stationName"
        ).value = "";

        document.getElementById(
            "location"
        ).value = "";

        document.getElementById(
            "city"
        ).value = "";

        document.getElementById(
            "power"
        ).value = "";


        await loadStations();

    } catch (error) {

        console.error(error);

        alert(
            "Unable to add station. " +
            "Check the backend."
        );
    }
}


/* =====================================================
   DELETE STATION
===================================================== */

async function deleteStation(id) {

    if (
        !confirm(
            "Delete this charging station?"
        )
    ) {
        return;
    }


    try {

        const response =
            await fetch(
                `${STATION_API}/${id}`,
                {
                    method: "DELETE"
                }
            );


        if (!response.ok) {

            throw new Error(
                "Delete failed"
            );
        }


        alert(
            "Station deleted successfully!"
        );


        await loadStations();

    } catch (error) {

        console.error(error);

        alert(
            "Unable to delete station."
        );
    }
}


/* =====================================================
   CHARGERS
===================================================== */

async function loadChargers() {

    try {

        const response =
            await fetch(CHARGER_API);


        if (!response.ok) {

            throw new Error(
                "Failed to load chargers"
            );
        }


        const chargers =
            await response.json();


        displayChargers(chargers);


        updateChargerStatistics(
            chargers
        );


        loadChargerDropdown(
            chargers
        );


    } catch (error) {

        console.error(error);

        document.getElementById(
            "chargerList"
        ).innerHTML = `
            <p class="loading">
                Unable to load chargers.
                Check the Spring Boot backend.
            </p>
        `;
    }
}


/* =====================================================
   DISPLAY CHARGERS
===================================================== */

function displayChargers(chargers) {

    const container =
        document.getElementById(
            "chargerList"
        );


    container.innerHTML = "";


    if (
        !chargers ||
        chargers.length === 0
    ) {

        container.innerHTML = `
            <p class="loading">
                No chargers available.
                Add a charger above.
            </p>
        `;

        return;
    }


    chargers.forEach(charger => {

        const card =
            document.createElement("div");


        card.className =
            "charger-card";


        let stationName =
            "Not assigned";


        if (
            charger.station &&
            charger.station.stationName
        ) {

            stationName =
                charger.station.stationName;

        }


        card.innerHTML = `

            <h3>
                🔌 ${escapeHtml(
            charger.chargerCode ||
            "Charger"
        )}
            </h3>

            <p>
                Type:
                ${escapeHtml(
            charger.chargerType ||
            "N/A"
        )}
            </p>

            <p>
                Power:
                ${charger.powerCapacity ?? 0}
                kW
            </p>

            <p>
                Connector:
                ${escapeHtml(
            charger.connectorType ||
            "N/A"
        )}
            </p>

            <p>
                Station:
                ${escapeHtml(
            stationName
        )}
            </p>

            <p>
                Status:
                <span class="status ${getStatusClass(charger.status)}">
                    ${escapeHtml(
            charger.status ||
            "UNKNOWN"
        )}
                </span>
            </p>

            <button
                class="delete-button"
                onclick="deleteCharger(${charger.id})">

                Delete

            </button>
        `;


        container.appendChild(card);

    });
}


/* =====================================================
   CHARGER STATISTICS
===================================================== */

function updateChargerStatistics(
    chargers
) {

    const total =
        chargers.length;


    const available =
        chargers.filter(
            charger =>
                String(
                    charger.status
                ).toUpperCase()
                === "AVAILABLE"
        ).length;


    const busy =
        chargers.filter(
            charger =>
                String(
                    charger.status
                ).toUpperCase()
                === "IN_USE"
        ).length;


    const faulty =
        chargers.filter(
            charger =>
                String(
                    charger.status
                ).toUpperCase()
                === "FAULTY"
        ).length;


    document.getElementById(
        "chargerCount"
    ).innerText = total;


    document.getElementById(
        "totalChargers"
    ).innerText = total;


    document.getElementById(
        "availableChargers"
    ).innerText = available;


    document.getElementById(
        "busyChargers"
    ).innerText = busy;


    document.getElementById(
        "faultyChargers"
    ).innerText = faulty;


    document.getElementById(
        "reportChargers"
    ).innerText = total;
}


/* =====================================================
   CREATE CHARGER
===================================================== */

async function createCharger() {

    const chargerCode =
        document.getElementById(
            "chargerCode"
        ).value.trim();


    const chargerType =
        document.getElementById(
            "chargerType"
        ).value;


    const powerCapacity =
        document.getElementById(
            "chargerPower"
        ).value;


    const connectorType =
        document.getElementById(
            "connectorType"
        ).value.trim();


    const status =
        document.getElementById(
            "chargerStatus"
        ).value;


    const stationId =
        document.getElementById(
            "chargerStation"
        ).value;


    if (
        !chargerCode ||
        !powerCapacity ||
        !connectorType
    ) {

        alert(
            "Please fill all required charger fields."
        );

        return;
    }


    if (!stationId) {

        alert(
            "Please select a charging station."
        );

        return;
    }


    const charger = {

        chargerCode:
        chargerCode,

        chargerType:
        chargerType,

        powerCapacity:
            Number(powerCapacity),

        connectorType:
        connectorType,

        status:
        status,

        stationId:
            Number(stationId)
    };


    try {

        const response =
            await fetch(
                CHARGER_API,
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(charger)
                }
            );


        if (!response.ok) {

            const errorText =
                await response.text();

            console.error(
                errorText
            );

            throw new Error(
                "Failed to create charger"
            );
        }


        alert(
            "Charger added successfully!"
        );


        document.getElementById(
            "chargerCode"
        ).value = "";

        document.getElementById(
            "chargerPower"
        ).value = "";

        document.getElementById(
            "connectorType"
        ).value = "";


        await loadChargers();

        await loadSessions();

    } catch (error) {

        console.error(error);

        alert(
            "Unable to add charger. " +
            "Check the backend and station."
        );
    }
}


/* =====================================================
   DELETE CHARGER
===================================================== */

async function deleteCharger(id) {

    if (
        !confirm(
            "Delete this charger?"
        )
    ) {
        return;
    }


    try {

        const response =
            await fetch(
                `${CHARGER_API}/${id}`,
                {
                    method: "DELETE"
                }
            );


        if (!response.ok) {

            throw new Error(
                "Delete failed"
            );
        }


        alert(
            "Charger deleted successfully!"
        );


        await loadChargers();

    } catch (error) {

        console.error(error);

        alert(
            "Unable to delete charger."
        );
    }
}


/* =====================================================
   CHARGER DROPDOWN FOR SESSIONS
===================================================== */

function loadChargerDropdown(
    chargers
) {

    const dropdown =
        document.getElementById(
            "sessionCharger"
        );


    dropdown.innerHTML = `
        <option value="">
            Select Charger
        </option>
    `;


    chargers.forEach(charger => {

        const option =
            document.createElement("option");


        option.value =
            charger.id;


        option.textContent =
            `${charger.chargerCode} - ${
                charger.status || "UNKNOWN"
            }`;


        dropdown.appendChild(
            option
        );

    });
}


/* =====================================================
   SESSIONS
===================================================== */

async function loadSessions() {

    try {

        const response =
            await fetch(
                SESSION_API
            );


        if (!response.ok) {

            throw new Error(
                "Failed to load sessions"
            );
        }


        const sessions =
            await response.json();


        displaySessions(
            sessions
        );


        updateSessionStatistics(
            sessions
        );


    } catch (error) {

        console.error(error);

        document.getElementById(
            "sessionList"
        ).innerHTML = `
            <p class="loading">
                Unable to load charging sessions.
                Check the Spring Boot backend.
            </p>
        `;
    }
}


/* =====================================================
   DISPLAY SESSIONS
===================================================== */

function displaySessions(
    sessions
) {

    const container =
        document.getElementById(
            "sessionList"
        );


    container.innerHTML = "";


    if (
        !sessions ||
        sessions.length === 0
    ) {

        container.innerHTML = `
            <p class="loading">
                No charging sessions available.
                Start a session above.
            </p>
        `;

        return;
    }


    sessions.forEach(session => {

        const card =
            document.createElement("div");


        card.className =
            "session-card";


        let chargerCode =
            "Not assigned";


        if (
            session.charger &&
            session.charger.chargerCode
        ) {

            chargerCode =
                session.charger.chargerCode;
        }


        const status =
            String(
                session.status ||
                "UNKNOWN"
            ).toUpperCase();


        let actions = "";


        if (status === "STARTED") {

            actions = `

                <button
                    class="stop-button"
                    onclick="stopSession(${session.id})">

                    ⏹ Stop Charging

                </button>

            `;
        }


        actions += `

            <button
                class="delete-button"
                onclick="deleteSession(${session.id})">

                Delete

            </button>

        `;


        card.innerHTML = `

            <h3>
                🚗 ${escapeHtml(
            session.sessionCode ||
            "Session"
        )}
            </h3>

            <p>
                Driver:
                ${escapeHtml(
            session.driverName ||
            "N/A"
        )}
            </p>

            <p>
                Charger:
                ${escapeHtml(
            chargerCode
        )}
            </p>

            <p>
                Start Reading:
                ${session.startMeterReading ?? 0}
            </p>

            <p>
                End Reading:
                ${session.endMeterReading ?? "-"}
            </p>

            <p>
                Energy:
                ${session.energyConsumed ?? 0}
                kWh
            </p>

            <p>
                Total Amount:
                ₹${session.totalAmount ?? 0}
            </p>

            <p>
                Status:
                <span class="status ${getStatusClass(status)}">
                    ${escapeHtml(status)}
                </span>
            </p>

            <div class="session-actions">

                ${actions}

            </div>
        `;


        container.appendChild(
            card
        );

    });
}


/* =====================================================
   SESSION STATISTICS
===================================================== */

function updateSessionStatistics(
    sessions
) {

    const total =
        sessions.length;


    const started =
        sessions.filter(
            session =>
                String(
                    session.status
                ).toUpperCase()
                === "STARTED"
        ).length;


    const completed =
        sessions.filter(
            session =>
                String(
                    session.status
                ).toUpperCase()
                === "COMPLETED"
        ).length;


    const energy =
        sessions.reduce(
            (sum, session) =>
                sum +
                Number(
                    session.energyConsumed ||
                    0
                ),
            0
        );


    const revenue =
        sessions.reduce(
            (sum, session) =>
                sum +
                Number(
                    session.totalAmount ||
                    0
                ),
            0
        );


    document.getElementById(
        "sessionCount"
    ).innerText = total;


    document.getElementById(
        "activeSessionCount"
    ).innerText = started;


    document.getElementById(
        "totalSessions"
    ).innerText = total;


    document.getElementById(
        "startedSessions"
    ).innerText = started;


    document.getElementById(
        "completedSessions"
    ).innerText = completed;


    document.getElementById(
        "totalEnergy"
    ).innerText =
        energy.toFixed(2);


    document.getElementById(
        "reportSessions"
    ).innerText = total;


    document.getElementById(
        "reportEnergy"
    ).innerText =
        energy.toFixed(2);


    document.getElementById(
        "reportRevenue"
    ).innerText =
        "₹" + revenue.toFixed(2);


    document.getElementById(
        "reportCompleted"
    ).innerText =
        completed;


    updateReportSummary(
        total,
        started,
        completed,
        energy,
        revenue
    );
}


/* =====================================================
   START SESSION
===================================================== */

async function startSession() {

    const sessionCode =
        document.getElementById(
            "sessionCode"
        ).value.trim();


    const driverName =
        document.getElementById(
            "driverName"
        ).value.trim();


    const startMeterReading =
        document.getElementById(
            "startMeterReading"
        ).value;


    const chargerId =
        document.getElementById(
            "sessionCharger"
        ).value;


    if (
        !sessionCode ||
        !driverName ||
        !startMeterReading
    ) {

        alert(
            "Please fill all session fields."
        );

        return;
    }


    if (!chargerId) {

        alert(
            "Please select a charger."
        );

        return;
    }


    const session = {

        sessionCode:
        sessionCode,

        driverName:
        driverName,

        startMeterReading:
            Number(
                startMeterReading
            ),

        chargerId:
            Number(
                chargerId
            )
    };


    try {

        const response =
            await fetch(
                SESSION_API,
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(session)
                }
            );


        if (!response.ok) {

            const errorText =
                await response.text();

            console.error(
                errorText
            );

            throw new Error(
                "Failed to start session"
            );
        }


        alert(
            "Charging session started successfully!"
        );


        document.getElementById(
            "sessionCode"
        ).value = "";

        document.getElementById(
            "driverName"
        ).value = "";

        document.getElementById(
            "startMeterReading"
        ).value = "";


        await loadSessions();

        await loadChargers();

    } catch (error) {

        console.error(error);

        alert(
            "Unable to start charging session. " +
            "Check the backend."
        );
    }
}


/* =====================================================
   STOP SESSION
===================================================== */

async function stopSession(
    id
) {

    const endReading =
        prompt(
            "Enter the end meter reading:"
        );


    if (
        endReading === null ||
        endReading.trim() === ""
    ) {

        return;
    }


    const endMeterReading =
        Number(
            endReading
        );


    if (
        isNaN(endMeterReading)
    ) {

        alert(
            "Please enter a valid meter reading."
        );

        return;
    }


    /*
       Tariff ID is optional in your
       ChargingSessionController.
    */

    const tariffId =
        prompt(
            "Enter Tariff ID (optional):"
        );


    const request = {

        endMeterReading:
        endMeterReading
    };


    if (
        tariffId !== null &&
        tariffId.trim() !== ""
    ) {

        request.tariffId =
            Number(
                tariffId
            );
    }


    try {

        const response =
            await fetch(
                `${SESSION_API}/${id}/stop`,
                {
                    method: "PUT",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(request)
                }
            );


        if (!response.ok) {

            const errorText =
                await response.text();

            console.error(
                errorText
            );

            throw new Error(
                "Failed to stop session"
            );
        }


        alert(
            "Charging session completed successfully!"
        );


        await loadSessions();

        await loadChargers();

    } catch (error) {

        console.error(error);

        alert(
            "Unable to stop charging session. " +
            "Check the backend and tariff."
        );
    }
}


/* =====================================================
   DELETE SESSION
===================================================== */

async function deleteSession(
    id
) {

    if (
        !confirm(
            "Delete this charging session?"
        )
    ) {

        return;
    }


    try {

        const response =
            await fetch(
                `${SESSION_API}/${id}`,
                {
                    method: "DELETE"
                }
            );


        if (!response.ok) {

            throw new Error(
                "Delete failed"
            );
        }


        alert(
            "Charging session deleted successfully!"
        );


        await loadSessions();

    } catch (error) {

        console.error(error);

        alert(
            "Unable to delete charging session."
        );
    }
}


/* =====================================================
   REPORT SUMMARY
===================================================== */

function updateReportSummary(
    total,
    started,
    completed,
    energy,
    revenue
) {

    const summary =
        document.getElementById(
            "reportSummary"
        );


    summary.innerHTML = `

        The system currently has
        <strong>${total}</strong>
        charging session(s).

        There are
        <strong>${started}</strong>
        active session(s) and
        <strong>${completed}</strong>
        completed session(s).

        A total of
        <strong>${energy.toFixed(2)} kWh</strong>
        of energy has been consumed.

        The recorded revenue is
        <strong>₹${revenue.toFixed(2)}</strong>.

    `;
}


/* =====================================================
   STATUS CLASS
===================================================== */

function getStatusClass(
    status
) {

    if (!status) {
        return "";
    }


    return "status-" +
        String(status)
            .toLowerCase()
            .replace("_", "-");
}


/* =====================================================
   HTML SAFETY
===================================================== */

function escapeHtml(
    value
) {

    if (
        value === null ||
        value === undefined
    ) {

        return "";
    }


    return String(value)
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}


/* =====================================================
   INITIAL LOAD
===================================================== */

window.addEventListener(
    "load",
    async function () {

        await loadDashboard();

    }
);