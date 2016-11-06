/*
 * SportChef – Sports Competition Management Software
 * Copyright (C) 2016 Marcus Fihlon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.sportchef.events.business.ping.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static spark.Spark.get;

public class PingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingResource.class);

    private final Route pong = (final Request request, final Response response) -> String.format("Pong from %s",
            getHostname());

    public PingResource() {
        get("/ping", pong);
    }

    private String getHostname() {
        String hostname = System.getenv("HOSTNAME");
        if (hostname == null) {
            try {
                hostname = InetAddress.getLocalHost().getHostName();
            } catch (final UnknownHostException e) {
                LOGGER.error("Unable to determine hostname.", e);
            }
        }
        return hostname;
    }

}
