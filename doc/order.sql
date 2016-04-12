--
-- PostgreSQL database dump
--

-- Started on 2009-05-04 20:23:07

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1495 (class 1259 OID 17461)
-- Dependencies: 6
-- Name: order; Type: TABLE; Schema: public; Owner: erp; Tablespace: 
--

CREATE TABLE "order" (
    id integer NOT NULL,
    create_date timestamp without time zone NOT NULL,
    status integer NOT NULL,
    fee real NOT NULL,
    comment character varying(64) NOT NULL,
    vendor_id integer NOT NULL,
    weight integer NOT NULL
);


ALTER TABLE public."order" OWNER TO erp;

--
-- TOC entry 1496 (class 1259 OID 17464)
-- Dependencies: 6 1495
-- Name: order_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE order_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.order_id_seq OWNER TO erp;

--
-- TOC entry 1783 (class 0 OID 0)
-- Dependencies: 1496
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE order_id_seq OWNED BY "order".id;


--
-- TOC entry 1776 (class 2604 OID 17509)
-- Dependencies: 1496 1495
-- Name: id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE "order" ALTER COLUMN id SET DEFAULT nextval('order_id_seq'::regclass);


--
-- TOC entry 1779 (class 2606 OID 17522)
-- Dependencies: 1495 1495
-- Name: order_p_key; Type: CONSTRAINT; Schema: public; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_p_key PRIMARY KEY (id);


--
-- TOC entry 1777 (class 1259 OID 17533)
-- Dependencies: 1495
-- Name: order_crdate_index; Type: INDEX; Schema: public; Owner: erp; Tablespace: 
--

CREATE INDEX order_crdate_index ON "order" USING btree (create_date);


--
-- TOC entry 1780 (class 1259 OID 17534)
-- Dependencies: 1495
-- Name: order_status_index; Type: INDEX; Schema: public; Owner: erp; Tablespace: 
--

CREATE INDEX order_status_index ON "order" USING btree (status);


-- Completed on 2009-05-04 20:23:07

--
-- PostgreSQL database dump complete
--

