package com.fatosmorina.reservations.web.application;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fatosmorina.reservations.business.domain.RoomReservation;
import com.fatosmorina.reservations.business.service.ReservationService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @MockBean
    private ReservationService reservationService;
    @Autowired
    private MockMvc mockMvc;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Test
    public void getReservations() throws Exception {
        Date date = DATE_FORMAT.parse("11-03-2017");
        List<RoomReservation> mockReservationList = new ArrayList<RoomReservation>();
        RoomReservation mockRoomReservation = new RoomReservation();
        mockRoomReservation.setFirstName("First");
        mockRoomReservation.setLastName("Guest");
        mockRoomReservation.setDate(date);
        mockRoomReservation.setGuestId(1l);
        mockRoomReservation.setRoomNumber("R1");
        mockRoomReservation.setRoomId(200l);
        mockRoomReservation.setRoomName("First Room");
        mockReservationList.add(mockRoomReservation);

        given(reservationService.getRoomReservationsForDate("11-03-2017")).willReturn(mockReservationList);
        this.mockMvc.perform(get("/reservations?date=11-03-2017"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Guest, First")));
    }
}
